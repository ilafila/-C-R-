package ru.pm.network;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

public class TCPconnection {

    // класс который реализовывает в себе одно TCP соединение
    // основной класс соединения(то есть одно соединение)

    // должны быть сокет который связан с соединением + поток который будет слушать входящее сообщение
    // (то есть у нас будет один поток на каждом клиенте и он будет слушать входящее соединение постоянно читать поток ввода)
    // и если строчка прилетела то он будет генерировать событие
    // сделать потоки ввода и вывода (то есть TCP connection который будет работать со строками)



    private final Socket socket;
    // слушатель событий
    private final TCPconnectionListener eventListener;
    private final Thread rxThread;
    private final BufferedReader in;
    private final BufferedWriter out;



    // конструктор для класса ClientWindow...(eventListner - штука которая будет слушать данное TCP соединение)
    public TCPconnection(TCPconnectionListener eventListener,String ipAdrr,int port) throws IOException{
        // мы из этого конструктора вызываем нижний с помощью такой конструкции
        this(eventListener, new Socket( ipAdrr, port));

    }


    // конструктор который на вход примет готовый объект сокета и с этим сокетом создаст соединение
    public TCPconnection(TCPconnectionListener eventListener,Socket socket) throws IOException {
        this.eventListener = eventListener;

        // запоминаем сокет
        this.socket = socket;
        // далее у этого сокета нужно получить входящий и исходящий потоки чтоб принимать оттуда какие-то байты и писать туда какие-то байты
        // получили простой поток ввода(getInputStream) и на его основе создали InputStreamReader и на его основе создали
        // объект класса BufferedReader который умеет читать и писать строчки
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
        // как создать и запустить новый поток который будет слушать все входящее
        // поток который слушает входящие соединения
        // поток что-то должен выполнять...для того чтобы он что-то выполнял-вариант передпть ему экземпляр анонимного класса
        // который реализует интерфейс Runnable
        rxThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // когда поток стартовал соединение готово
                    eventListener.onConnectionReady(TCPconnection.this);
                    // пока поток не прерван получаем строчку и отдаем ее ивентлистнеру
                    while(!rxThread.isInterrupted()){
                        eventListener.onReceiveString(TCPconnection.this, in.readLine());
                    }

                }catch (IOException e){
                    eventListener.onException(TCPconnection.this, e);

                }finally {
                    eventListener.onDisconnect(TCPconnection.this);

                }


            }
        });
        // запускаем поток
        rxThread.start();
    }



    // синхронизируем чтоб безопасно обращаться из разных потоков
    public synchronized void sendString(String value){
        try{
            out.write(value + "\r\n");
            // когда мы сделали метод sendString хотим чтоб строчка передалась
            // мы это принудительно делаем командой flush
            // то есть команда flush сбрасывает все буфферы и отправляет
            out.flush();
        }catch (IOException e){
            eventListener.onException(TCPconnection.this, e);
            disconnect();
        }
    }

    public synchronized void disconnect(){
        // прерываем поток
        rxThread.interrupt();
        try{
            // и закрываем сокет
            socket.close();
        }catch (IOException e){
            eventListener.onException(TCPconnection.this, e);
        }
    }

    @Override
    public String toString(){
        return "TCPconnection: " + socket.getInetAddress() + ": " + socket.getPort();
    }

}
