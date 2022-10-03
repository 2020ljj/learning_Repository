#pragma once
#ifndef S_SERVER_WITHWIDGETS_H
#define S_SERVER_WITHWIDGETS_H
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
private:
    int number;
    //QWidget* father;
    QString str;
protected:
    void run();
    
signals:
    void UpdateSignal(QString Str);
    void UpdateMessage(QString Str);
    void UpdateReview(QString Str);
public slots:
    //void UpdateSlotReview(QString Str);

public:
    void SetReview(QString Str);
};


class S_server_withwidget : public QMainWindow
{
    Q_OBJECT

public:
    S_server_withwidget(QWidget *parent = nullptr);
    ~S_server_withwidget();
    Thread* myThread;
    QPushButton* startButton;
    QPushButton* reviewButton;
    QString str;
    QLineEdit *notice;
    QLineEdit *review;
    QTextEdit *message;
    QLabel *l1, *l2, *l3;
public slots:
    void StartSlot();
    void SendMessageSlot();
    void UpdateSlot(QString Str);
    void UpdateSlotMessage(QString Str);
private:
    //Ui::S_server_withwidgetClass ui;
};
class LogWidget : public QWidget
{
    Q_OBJECT;
public:
    explicit LogWidget(QWidget* parent = nullptr);

protected:
    void confirm();
    void clear();
signals:
    void sendData(QString);   //用来传递数据的信号
private:
    QPushButton b1, b2;
    QLabel label, label1, label2;                       //用于显示行文本编辑器的输入提示
    QLineEdit Line1, Line2;
    QString admin, secret;
    S_server_withwidget *myWidget;
    bool result;
};
#endif//S_SERVER_WITHWIDGETS_H