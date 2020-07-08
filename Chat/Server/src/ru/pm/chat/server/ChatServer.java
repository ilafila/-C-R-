package ru.pm.chat.server;
import ru.pm.network.TCPconnection;
import ru.pm.network.TCPconnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer implements TCPconnectionListener {

    // cервер - сущность которая принимает соединения и умеет их держать
    // и сможет рассылать сообщения от одного клиента другим клиентам
    // для работы нужно 2 класса  ServerSocket который умеет слушать входящее соединение +
    // принимать его + создавать объект сокета который связан с этим соединением + готовый сокет нам отдавать
    // с помощью же класса Socket соединение можно устанавливать

    public static void main(String[] args){
        new ChatServer();
    }
    // мейн статический метод и точка входа в программу

    private final List<TCPconnection> connections = new ArrayList<>();


    public ChatServer(){
        System.out.println("Server running...");
        //   класс умеет слушать порт и принимать входящее соединение
        try(ServerSocket serverSocket = new ServerSocket(8189)){
            //  try конструкция с ресурсами - умеет закрывать ресурсы которые захвачены
            while (true){
                // бесконечный цикл - в нем сервер принимает входящие соединения то есть создает  tcpconnection
                //мы создаем сервер сокет котороый слушсает тср порт 8189
                //в бесконечном цикле мы постоянно висим в методе акцепт
                //метод акцепт ждет нового соединения и как только оно установилось он возвращает объект сокета
                //мы тут же этот объект сокета передаем в конструктори тсрконекшна включая себя как листнер
                // создаем его экземпляр
                //   этот простой сервер не расчитан на управление извне
                try{
                    // ловит исключения при подключении клиентов
                    new TCPconnection(this, serverSocket.accept()); //передали себя и сокет (сначала надо прописать зависимость от network сервера)
                    // передаем в тср готовый сокет и слушаетль --> делаем из класса чатсервер слушателя
                }catch (IOException e){
                    System.out.println("TCPconnection exception: " + e);
                }

            }

        }catch (IOException e){
            throw new RuntimeException(e);

        }
    }
    //implements TCPconnectionListener -- мы говорим что чатсервер имплементирует тср -
    //после этого появятся  описания всех методов из интерфейса тср
    // до реализации методов мы являемся и чатсервером и тсрконэешен листером

    //обработка событий
    //так как клиентов(потоков) очень много то synchronized потоки чтоб одновременно из разных потоков нельзя было в них
    // попасть ибо это может как-то нарушить их работу

    @Override
    public synchronized void onConnectionReady(TCPconnection topConnection){
        // когда коннекшн готов мы добавляем его в список
        connections.add(topConnection);
        sendToAllConnections("Client connected: " + topConnection);


    }

    @Override
    public synchronized void onReceiveString(TCPconnection topConnection, String value){
        // если мы приянли строчку нам нужно разослать ее всем клиентам
        sendToAllConnections(value);

    }

    @Override
    public synchronized void onDisconnect(TCPconnection topConnection){
        // если коннекшн отвалился то удаляем его
        connections.remove(topConnection);
        sendToAllConnections("Client disconnected: " + topConnection);



    }

    @Override
    public synchronized void onException(TCPconnection topConnection,Exception e){
        System.out.println("TCPconnection exception: " + e);

    }

    private void sendToAllConnections(String value){
        System.out.println(value);
        for(int i = 0;i < connections.size();i++){
            connections.get(i).sendString(value);

        }
    }
}
