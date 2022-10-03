#pragma once
#ifndef S_CLIENT_WITHWIDGETS_H
#define S_CLIENT_WITHWIDGETS_H
#pragma comment(lib,"ws2_32.lib")
#include<iostream>
#include<winsock2.h>
#include<windows.h>
#include <WS2tcpip.h>
#include <QMainWindow>
#include <QtWidgets>
#include <QThread>
#include <QPushButton>
#include <QLabel>
#include <QLineEdit>
#include <QTextEdit>
#include <QVBoxLayout>
#include <QHBoxLayout>

class Thread : public QThread
{
    Q_OBJECT
public:
    Thread(QObject* parent = 0);
    ~Thread();
    void SetReview(QString Str);
    //void SetService_ip(char*);
    void run();
private:
    int number;
    int messageFlag;
    //char Service_ip[15];
    QString str;
protected:
   
signals:
    void UpdateSignal(QString Str);
    void UpdateMessage(QString Str);
    void UpdateReview(QString Str);

};


class S_client_withwidget : public QMainWindow
{
    Q_OBJECT

public:
    S_client_withwidget(QWidget* parent = nullptr);
    ~S_client_withwidget();
    Thread* myThread;
    QPushButton* startButton;
    QPushButton* reviewButton;
    QString str;
    QLineEdit* notice;
    QLineEdit* review;
    QTextEdit* message;
    QLabel* l1, * l2, * l3;
public slots:
    void StartSlot();
    void SendMessageSlot();
    void UpdateSlot(QString Str);
    void UpdateSlotMessage(QString Str);
private:
    //Ui::S_server_withwidgetClass ui
};
#endif//S_SERVER_WITHWIDGETS_H