#include "S_client_withwidget.h"

#define BUF_SIZE 1024
using namespace std;
void sendFile(SOCKET clntSock, FILE* fp) {
	char buffer[BUF_SIZE] = { 0 }; //缓冲区
	char fin[BUF_SIZE] = { 1 };
	int nCount;
	while ((nCount = fread(buffer, 1, BUF_SIZE, fp)) > 0) {
		send(clntSock, buffer, nCount, 0);
	}
	send(clntSock, fin, 1024, 0);
	shutdown(clntSock, SD_SEND); //文件读取完毕，断开输出流，向客户端发送FIN包
	recv(clntSock, buffer, BUF_SIZE, 0); //阻塞，等待客户端接收完毕

	fclose(fp);
}
S_client_withwidget::S_client_withwidget(QWidget* parent)
	: QMainWindow(parent)
{
	setWindowTitle(u8"Socket_客户端");
	resize(500, 600);
	myThread = new Thread;
	startButton = new QPushButton(u8"开始连接");
	reviewButton = new QPushButton(u8"回复/确认");
	notice = new QLineEdit();
	notice->setText(u8"系统提示：请务必确认服务端正在监听后再点击‘开始连接’");
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
	l2->move(10, 80);
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

S_client_withwidget::~S_client_withwidget()
{

}
void S_client_withwidget::StartSlot()
{
	myThread->start();
}
void S_client_withwidget::UpdateSlot(QString Str) {
	notice->setText(Str);
}
void S_client_withwidget::UpdateSlotMessage(QString Str)
{
	message->append(Str);
}
void S_client_withwidget::SendMessageSlot() {

	char* filename;
	QString temp = review->text();
	QByteArray ba = temp.toLatin1(); 
	filename = ba.data();
	FILE* fp = fopen(filename, "r");//rb
	if (fp == NULL)					//如果不能打开默认发送消息
	{
		message->append(u8"你回复了：" + temp);
		myThread->SetReview(temp);
		review->clear();

	}
	else							//如果能打开默认发送文件
	{
		message->append(u8"你发送了文件：" + temp);
		myThread->SetReview(temp);
		review->clear();
	}
}
/////////////****************////////////////
Thread::Thread(QObject* parent) : QThread(parent) {
	messageFlag = 0;
	//Service_ip = NULL;
	number = 0;          //发送完消息后number置0，回复框消息就绪后置1（即number == 1 时 才能回复）
}
Thread::~Thread()
{
}
void Thread::SetReview(QString Str) {
	str = Str;
	number = 1;
}
void Thread ::run() {
	int send_len = 0;
	int recv_len = 0;
	//定义发送缓冲区和接受缓冲区
	char send_buf[BUF_SIZE];
	char recv_buf[BUF_SIZE];
	//定义服务端套接字，接受请求套接字
	SOCKET s_server;
	SOCKADDR_IN server_addr;
	WORD w_req = MAKEWORD(2, 2);//版本号
	WSADATA wsadata;
	int err;
	err = WSAStartup(w_req, &wsadata);
	if (err != 0) {
		str = u8"系统提示：初始化套接字库失败！";
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
		WSACleanup();
	}
	else {
		str = u8"系统提示：套接字库版本正确！";
		emit UpdateSignal(str);
		sleep(1);
	}
	/*str = u8"系统提示：输入服务器的IP：";
	emit UpdateSignal(str);
	sleep(1);
	while (messageFlag == 0) {
		sleep(1);
	}*/
	//char Service_ip[15];
	server_addr.sin_family = AF_INET;
	server_addr.sin_addr.S_un.S_addr = inet_addr("127.0.0.1");
	server_addr.sin_port = htons(12341);
	s_server = socket(AF_INET, SOCK_STREAM, 0);
	if (::connect(s_server, (SOCKADDR*)&server_addr, sizeof(SOCKADDR)) == SOCKET_ERROR) {
		str = u8"系统提示：服务器连接失败！";
		emit UpdateSignal(str);
		sleep(1);
		WSACleanup();
	}
	else {
		str = u8"系统提示：服务器连接成功！";
		emit UpdateSignal(str);
		sleep(1);
		str = u8"在回复区输入消息回复信息，输入文件地址传送文件！";
		emit UpdateSignal(str);
		sleep(2);
	}
	while (1) {
		while (1) {
			if (number == 0)
				Sleep(1000);
			else {
				
				char* filename;
				QByteArray ba = str.toLatin1();
				filename = ba.data();
				FILE* fp = fopen(filename, "rb");//rb
				if (fp == NULL)					//如果不能打开发送消息
				{
					string s = str.toStdString();
					const char* send_buf1 = s.c_str();
					send_len = send(s_server, send_buf1, BUF_SIZE, 0);
					if (send_len < 0) {
						str = u8"发送失败！";
						emit UpdateSignal(str);
						break;
					}
				}
				/*else							//如果能打开发送文件
				{
					send(s_server, filename, BUF_SIZE, 0);	//发送文件名和地址
					sendFile(s_server, fp);
					/ / char head[BUF_SIZE] = "[file]";
					//循环发送数据，直到文件结尾
					char buffer[BUF_SIZE] = { 0 }; //缓冲区
					char fin[BUF_SIZE] = { 1 };
					int nCount;
					/*send(s_server, head, 1024, 0);					//发送head
					sleep(1);
						send(s_server, filename, 1024, 0);	//发送文件名和地址
					sleep(1);
					while ((nCount = fread(buffer, 1, BUF_SIZE, fp)) > 0) {
						send(s_server, buffer, nCount, 0);
					}
					shutdown(s_server, SD_SEND); //文件读取完毕，断开输出流，向客户端发送FIN包
					send(s_server, fin, 1024, 0);

			}*/
				number = 0;      //发送完消息后number置0，接收到消息后置1（即number == 1 时 才能回复）
				break;
			}

		}
		
		recv_len = recv(s_server, recv_buf, BUF_SIZE, 0);
		if (recv_len < 0) {
			str = u8"系统提示：接受失败或连接断开！";
			emit UpdateSignal(str);
			sleep(1);
			break;
		}
		else {
			str = u8"服务端回复：";
			str.append(recv_buf);
			emit UpdateMessage( str);
		}
	}
}