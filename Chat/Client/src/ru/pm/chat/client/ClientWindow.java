package ru.pm.chat.client;
import ru.pm.network.TCPconnection;
import ru.pm.network.TCPconnectionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



public class ClientWindow extends JFrame implements ActionListener, TCPconnectionListener {
  
  private static final String ipAddrr = "172.20.197.223";
  private static final int pOrt = 8189;
  private static final int width = 600;
  private static final int height = 400;




    public static void main(String[] args){
        // во всех графических интерфейсах есть ограничение по многопоточности
        // в свинге можно работать только из потока едт
        // с помощью такой констркуции заставляем клиентское окно исполняться в потоке едт
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientWindow();

            }
        });

    }

    // облсасть где будет текст
    private final JTextArea log = new JTextArea();
    // область куда будем писать имя
    private final JTextField fieldNickname = new JTextField("Ilia");
    // область куда мы будем писать текст сооющения
    private final JTextField fieldInput = new JTextField();



    // устанавливаем соединение
    private TCPconnection connection;


    // создаем пользовательское окно

    private ClientWindow(){
        // настраиваем окно
        // по крестику заркываем
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // устанавливаем размеры окна
        setSize(width, height);
        // делаем так чтоб окно всегда открывалось по центру
        setLocationRelativeTo(null);
        // делаем так чтоб окно всегда было сверху и не убиралось под дрго
        setAlwaysOnTop(true);


        // запрещаем редактирования в центральной обслати
        log.setEditable(false);
        // настраиваем автоматичексий перенос слов
        log.setLineWrap(true);
        // добавляем область в центр нашего окна
        add(log, BorderLayout.CENTER);


        // добавляем возможность перехвата событй(отправка по enter)
        fieldInput.addActionListener(this);
        // добавляем область ввода на юг
        add(fieldInput, BorderLayout.SOUTH);
        // область никнейма на север
        add(fieldNickname, BorderLayout.NORTH);


        // делаем окно видимым
        setVisible(true);


        // когда создается окно начинаем использовать соединение
        try{
            connection = new TCPconnection(this, ipAddrr, pOrt);
        }catch(IOException e){
            printMsg("Connection exception: " + e);
        }
    }


    // по нажатию на кнопку передать сообщение

    @Override
    public void actionPerformed(ActionEvent e){
        String msg = fieldInput.getText();
        if (msg.equals(""))
            return;
        // стираем то что в поле инпут
        fieldInput.setText(null);
        connection.sendString(fieldNickname.getText() + ": " + msg);
    }
  
  // методы не имеет смысла синхронизтировать так как оин все из одного потока(одно соединение)
    @Override
    public  void onConnectionReady(TCPconnection topConnection){
        printMsg("Connection is ready...");

    }

    @Override
    public  void onReceiveString(TCPconnection topConnection, String value){
        printMsg(value);

    }

    @Override
    public  void onDisconnect(TCPconnection topConnection){
        printMsg("Connection close");

    }

    @Override
    public  void onException(TCPconnection topConnection,Exception e){
        printMsg("Connection exception: " + e);

    }

    // метод который будет писать в текстовое поле и будем работать с ним из разных потоков
    // то есть из потока окошка + из потока соединения поэтому мы его синхр
    private synchronized void printMsg(String msg){
        // так как метод может вызывваться из разных потоков в том числе из потока самого соединения
        // статический публичный метод invok...которому можно передать Runnable в этом раннабле что-то написать
        // и вот это что-то гарантировано в потоке окна будет выполнено
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + "\n");
                // для того чтобы текст всегда автоматичекси поднимался
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }
}
