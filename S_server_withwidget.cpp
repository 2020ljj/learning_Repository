#include "S_server_withwidget.h"
#pragma comment(lib,"ws2_32.lib")
#include<iomanip>
#include<iostream>
#include<windows.h>

#define BUF_SIZE 1024
char fin[BUF_SIZE] = { 1 };
using namespace std;
/*bool ifFin(char* buffer) {
	int n = sizeof(buffer);
	bool b = true;
	for (int i = 0; i < n; i++) {
		if (buffer[i] != '1') {
			return false;
		}
	}
	return true;
}*/
void receiveFile(SOCKET sock, FILE* fp) {
	char buffer[BUF_SIZE] = { 0 }; //文件缓冲区
	int nCount;
	while ((nCount = recv(sock, buffer, BUF_SIZE, 0)) > 0) {
		/*if (strcmp(buffer, fin) == 0)
			break;
		else*/
			fwrite(buffer, nCount, 1, fp);
	}
	fclose(fp);
}
S_server_withwidget::S_server_withwidget(QWidget *parent)
    : QMainWindow(parent)
{
	
    setWindowTitle(u8"Socket_服务端登录");   
    resize(500, 600);                         
    myThread = new Thread;
    startButton = new QPushButton(u8"开始监听");
	reviewButton = new QPushButton(u8"回复");
    notice = new QLineEdit();
	message = new QTextEdit();
	review = new QLineEdit();
	l1 = new QLabel();
	l2 = new QLabel();
	l3 = new QLabel();
    notice->setParent(this);
    notice->move(20, 40);
    notice->resize(460, 40);
    notice->show();

	message->setParent(this);
	message->move(20, 120);
	message->resize(460, 180);
	message->show();
	
	review->setParent(this);
	review->move(20, 340);
	review->resize(460, 40);
	review->show();


    startButton->setParent(this);
    startButton->move(160, 450);
    startButton->resize(100, 40);
    startButton->show();

	reviewButton->setParent(this);
	reviewButton->move(300, 450);
	reviewButton->resize(100, 40);
	reviewButton->show();

	l1->setParent(this);
	l1->setText(u8"提示框：");
	l1->move(10, 0);
	l1->resize(100, 40);
	l1->show();

	l2->setParent(this);
	l2->setText(u8"聊天框：");
	l2->move(10,80);
	l2->resize(100, 40);
	l2->show();

	l3->setParent(this);
	l3->setText(u8"回复区：");
	l3->move(10, 300);
	l3->resize(100, 40);
	l3->show();

    connect(startButton, SIGNAL(clicked()), this, SLOT(StartSlot()));
	connect(reviewButton, SIGNAL(clicked()), this, SLOT(SendMessageSlot()));
    connect(myThread, SIGNAL(UpdateSignal(QString)), this, SLOT(UpdateSlot(QString)));
	connect(myThread, SIGNAL(UpdateMessage(QString)), this, SLOT(UpdateSlotMessage(QString)));
}

S_server_withwidget::~S_server_withwidget()
{

}
void S_server_withwidget::StartSlot()
{
    myThread->start();
}
void S_server_withwidget::SendMessageSlot()
{
	QString temp;
	temp = review->text();
	message->append(u8"你回复了："+temp);
	myThread->SetReview(temp);
	review->clear();
}

void S_server_withwidget::UpdateSlot(QString Str)
{
    notice->setText(Str);
}
void S_server_withwidget::UpdateSlotMessage(QString Str)
{
	message->append(Str);
}


//////*************//////
Thread::Thread(QObject* parent)
{
    number = 0;

}

Thread::~Thread()
{
}
void Thread::SetReview(QString Str) {
	str = Str;
	number = 1;
}
/*
void Thread::UpdateSlotReview(QString Str)
{
	str = Str;
	number = 1;

}*/
void Thread::run()
{
	SOCKET s_server;
	SOCKET s_accept;
    WORD w_req = MAKEWORD(2, 2);		//版本号		//WORD是2byte的无符号整数
    WSADATA wsadata;
    int err;
    err = WSAStartup(w_req, &wsadata);
	
	//定义长度变量
	int send_len = 0;
	int recv_len = 0;
	int len = 0;
	//定义发送缓冲区和接受缓冲区
	char recv_buf[BUF_SIZE] = { 0 };
	char addr[20];
	char recv_f[BUF_SIZE] = "[file]";
	//定义服务端套接字，接受请求套接字
	//服务端地址客户端地址
	SOCKADDR_IN server_addr;
	SOCKADDR_IN accept_addr;
    if (err != 0) {
        str = u8"系统提示：初始化套接字库失败！" ;
        emit UpdateSignal(str);
        sleep(1);
    }
    else {
        str = u8"系统提示：初始化套接字库成功！";
        emit UpdateSignal(str);
        sleep(1);

    }
    //检测版本号
    if (LOBYTE(wsadata.wVersion) != 2 || HIBYTE(wsadata.wHighVersion) != 2) {
        str = u8"系统提示：套接字库版本号不符！";
        emit UpdateSignal(str);
        sleep(1);
    }
    else {
        str = u8"系统提示：套接字库版本正确！！";
        emit UpdateSignal(str);
        sleep(1);
    }
	
	//填充服务端信息	
	server_addr.sin_family = AF_INET;					 //地址族：默认为AF_IENT
	server_addr.sin_addr.S_un.S_addr = htonl(INADDR_ANY);//32位IP地址;	INADDR_ANY = inet_addr("0.0.0.0")
	server_addr.sin_port = htons(12341);				//socket对应的16位TCP/UDP端口号
	//htons() 是网络字节序与主机字节序之间转换的函数
//创建套接字
	s_server = socket(AF_INET, SOCK_STREAM, 0);
	// AF_INET 表示采用TCP/IP协议族; SOCK_STREAM 表示采用TCP协议; 0是通常的默认情况
	if (::bind(s_server, (SOCKADDR*)&server_addr, sizeof(SOCKADDR)) == SOCKET_ERROR) {
		// 将socket绑定到某个IP和端口（IP标识主机，端口标识通信进程）
		str = u8"系统提示：套接字绑定失败！";
		emit UpdateSignal(str);
		sleep(1);

		WSACleanup();
	}
	else {
		str = u8"系统提示：套接字绑定成功！";
		emit UpdateSignal(str);
		sleep(1);
	}
	//设置套接字为监听状态, SOMAXCONN(设定是5)表示等待连接队列的最大长度
	if (listen(s_server, SOMAXCONN) < 0) {
		str = u8"系统提示：设置监听失败！";
		emit UpdateSignal(str);
		sleep(1);
		WSACleanup();
	}
	else {
		str = u8"系统提示：设置监听成功！";
		emit UpdateSignal(str);
		sleep(1);

	}
	str = u8"系统提示：服务端正在监听连接，请稍等---";
	emit UpdateSignal(str);
	//接受连接请求
	len = sizeof(SOCKADDR);
	s_accept = accept(s_server, (SOCKADDR*)&accept_addr, &len);
	if (s_accept == SOCKET_ERROR) {
		str = u8"系统提示：连接失败！";
		emit UpdateSignal(str);
		sleep(1);
		WSACleanup();
		return;
	}
	str = u8"系统提示：连接建立，准备接受数据：";
	emit UpdateSignal(str);
	string temp;
	
	temp.append(u8"     IP:port——");
	temp.append(to_string(ntohl(server_addr.sin_addr.S_un.S_addr)));
	temp.append(u8":");
	temp.append(to_string(ntohs(server_addr.sin_port)));
	str = QString::fromStdString(temp);
	emit UpdateSignal(str);
	while (1) {
		recv_len = recv(s_accept, recv_buf, BUF_SIZE, 0);//接收真正的数据包
		if (recv_len < 0) {
			str = u8"系统提示：接受失败";
			emit UpdateSignal(str);
			sleep(1);
			break;
		}

		else {
			//传文件失败
			/*if (recv_buf[1] == ':') {				//判断客户端发送的是文件
				str = u8"系统提示：接收文件！";
				emit UpdateSignal(str);
				sleep(1);
				//recv_len = recv(s_accept, recv_buf, BUF_SIZE, 0);				//接收文件名
				char filename[] ="D:\\1115555664.txt";							//保存位置和文件名
				FILE* fp = fopen(filename, "wb"); //以二进制方式打开（创建）文件
				receiveFile(s_accept, fp);
			}*/
			inet_ntop(AF_INET, (void*)&accept_addr.sin_addr, addr, sizeof(addr));
			temp = u8"客户端";
			temp.append(addr);
			temp.append(":");
			temp.append(recv_buf);
			str = QString::fromStdString(temp);
			emit UpdateMessage(str);
			str = u8"请在回复框内输入回复信息：";
			emit UpdateSignal(str);
			sleep(1);

			while (1) {
				if (number == 0)
					Sleep(1000);
				else {
					if (str == "#")
						break;
					else {
						string s = str.toStdString();
						const char* send_buf1 = s.c_str();
						send_len = send(s_accept, send_buf1, 1024, 0);
						if (send_len < 0) {
							str = u8"发送失败！";
							emit UpdateSignal(str);
							break;
						}
						number = 0;      //发送完消息后number置0，接收到消息后置1（即number == 1 时 才能回复）
						break;
					}
					
				}

			}
			}
			
		}
	}

/// <summary>
/// //////***********///////////
/// </summary>
/// <param name="parent"></param>
LogWidget::LogWidget(QWidget* parent)
	:QWidget(parent)
{
	myWidget = new S_server_withwidget;
	result = 0;
	setWindowTitle(u8"Socket_服务端登录");   //设置窗口标题
	resize(500, 325);                 //设置窗口大小
	b1.setParent(this); b2.setParent(this);            //设置父类
	b1.setText(u8"确认"); b2.setText(u8"重置");    //设置按钮内容
	b1.move(150, 200); b2.move(250, 200);                   //设置按钮位置
	b1.resize(80, 40); b2.resize(80, 40);               //设置按钮大小
	//设置按钮样式
	b1.show(); b2.show();                                            //设置按钮显示在父类窗口
	label.setParent(this);                          //设置父类
	label.setText(u8"请输入用户名和密码：");        //设置提示内容
	label.move(70, 25);                            //设置提示位置
	label.resize(200, 50);                          //设置提示大小
	label.setStyleSheet("font:15px;");              //设置提示界面的样式
	label.show();                                   //设置提示界面显示在父类窗口

	label1.setParent(this);                          //设置父类
	label1.setText(u8"用户名：");        //设置提示内容
	label1.move(40, 75);                            //设置提示位置
	label1.resize(200, 50);                          //设置提示大小
	label1.setStyleSheet("font:15px;");              //设置提示界面的样式
	label1.show();

	label2.setParent(this);                          //设置父类
	label2.setText(u8"密码：");						//设置提示内容
	label2.move(40, 130);                            //设置提示位置
	label2.resize(200, 50);                          //设置提示大小
	label2.setStyleSheet("font:15px;");              //设置提示界面的样式
	label2.show();

	Line1.setParent(this);
	Line1.move(100, 75);                            //设置提示位置
	Line1.resize(300, 40);                          //设置提示大小
	Line1.setStyleSheet("font:15px;");              //设置提示界面的样式
	Line2.setParent(this);
	Line2.move(100, 130);                            //设置提示位置
	Line2.resize(300, 40);                          //设置提示大小
	Line2.setStyleSheet("font:15px;");              //设置提示界面的样式

	connect(&b1, &QPushButton::clicked, this, &LogWidget::confirm);
	connect(&b2, &QPushButton::clicked, this, &LogWidget::clear);
}
void LogWidget::confirm() {
	admin = Line1.text();
	secret = Line2.text();
	if (admin == "admin" && secret == "123456") {

		QMessageBox qmb(QMessageBox::Warning, u8"提示", u8"密码验证正确！", QMessageBox::Close);
		qmb.resize(200, 300);
		qmb.exec();
		myWidget->show();
	}
	else {
		result = 0;
		QMessageBox qmb(QMessageBox::Warning, u8"提示", u8"密码验证错误！", QMessageBox::Close);
		qmb.resize(200, 300);
		qmb.exec();
		Line1.clear();
		Line2.clear();
	}
}
void LogWidget::clear() {
	Line1.clear();
	Line2.clear();
}
