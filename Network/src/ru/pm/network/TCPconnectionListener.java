package ru.pm.network;

public interface TCPconnectionListener {
    // в TCP соединении могу твозникнуть следущие ситуации

    // соединение готово
    void onConnectionReady(TCPconnection topConnection);
    // соединение приняло строчку входящую
    void onReceiveString(TCPconnection topConnection, String value);
    // соединение порвалось
    void onDisconnect(TCPconnection topConnection);
    // ошибка
    void onException(TCPconnection topConnection,Exception e);
}
