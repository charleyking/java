
import  java.net.*;
import  java.io.*;
import  java.util.*;

public class chatserverthree   implements  Runnable
 {  public  static final int  PORT=1252;   
     protected ServerSocket  listen;       //定义服务器端套接字listen
     static Vector connections;     //向量类connections存放与服务器连接的客户线程列表
     Thread  connect;   //定义服务器端线程
     
     //服务器构造初始化线程
     public chatserverthree()
      {  try   
            {     listen=new ServerSocket(PORT);        }  //使用本地IP地址创建一个服务器
         catch(UnknownHostException e2)  {  System.err.println("erro:"+e2);   }
         catch(IOException  e)
            {  System.err.println("erro:"+e);     System.exit(1);       }
          connections=new  Vector(1000);   
          connect=new  Thread(this);   
          connect.start();       //服务器端线程启动
      }
      
      //服务器main()初始化   
     public  static void main(String args[])
         {  new  chatserverthree();  
            System.out.println("Chat Server is starting!......");   }
      
      //服务器线程connect操作run方法
     public  void run()
      {  try
           {  while(true)   //始终监听来自网络端口的信息
               {   Socket  client=listen.accept();
                    firstthread   f=new firstthread(this,  client);   //为每一个人分别启动一个客户端线程
                    f.setPriority(Thread.MIN_PRIORITY);  
                    f.start();     //客户端线程启动
                    connections.addElement(f);    //将客户端线程加入向量列表中
                }
           }
          catch(IOException  e)
             {  System.err.println("Erro:"+e);
                 System.exit(1);
              }
        }
       
      //向聊天室所有人员发送普通话语信息  
      public void  broadcast(String  msg)
        {   int i;    firstthread   you;
            for(i=0;i<connections.size();i++)
             {  you=(firstthread)connections.elementAt(i);
                 try
                   {      you.out.writeUTF(msg);      }
                 catch(IOException  e)    {    }
              }
          }
          
        // 处理悄悄话，向特定人员发送悄悄话
         public  void broadcast1(String  msg)
           {   int i;     String   s1,s2,s3;
               firstthread  you;     s1=new  String("PEOPLE");
               s2=new  String(msg.substring(4));  //悄悄地对为4个字符
               s3=s1.concat(s2);
               for(i=0;i<connections.size();i++)
                 {   you=(firstthread)connections.elementAt(i);
                     if(s3.startsWith(you.name)) // name格式为：PEOPLE+名字+[性别]
                       {   try
                             {       you.out.writeUTF(msg);         }
                           catch(IOException  e)    {   } 
                        }
                   }
             }
  }
 
 //客户端线程类firstthread 
 class firstthread  extends Thread
   {   protected  Socket client;   
       String  line,  name;        //line读取来自客户端的消息；     name格式为：PEOPLE+名字+[性别]
       protected  DataOutputStream  firstout,  out;   // 定义网络数据输出流
       protected  chatserverthree server;
       protected  DataInputStream   in;     // 定义网络数据输入流
       
       //firstthread初始化线程
       public firstthread(chatserverthree  server,Socket  client)
        {  this.server=server;    this.client=client;
           try
             {  in=new DataInputStream(client.getInputStream());
                out=new DataOutputStream(client.getOutputStream());
                firstout=new DataOutputStream(client.getOutputStream());
              }
             catch(IOException  e)
               {  try 
                     {  server.connections.removeElement(this);
                         client.close();
                      }
                  catch(IOException e2)
                     {  System.err.println("有问题哦:"+e);
                         return;
                      } 
               }
              if(this.client==null)
                 {     server.broadcast("QUIT"+this.name);   this.name=null;      }
          }
        public void run()
           {   try
                 {   //客户端线程初始化运行读取服务器端的已有聊天室人员列表信息
                 	 for(int i=0;i<server.connections.size();i++)
                      {  firstthread  c=(firstthread)(server.connections.elementAt(i));
                          if( c.name!=null)
                            {   try     {     firstout.writeUTF(c.name+"*");      }
                                 catch(IOException e)    {    }
                            }
                       }
                  }
                  catch(ArrayIndexOutOfBoundsException  e)   {   }
                  catch(NullPointerException e)    {   }
                  
                  //客户端线程始终在监听的操作方法
                  try  
                     {   while(true)
                          {  line=in.readUTF();    // line读取来自客户端线路的信息
                          
                       //线路信息前端为PEOPLE表明有新人进入了聊天室
                              if(line.startsWith("PEOPLE")) 
                               {   try
                                       	//获取当前对象的线程
           {   firstthread  d=(firstthread)(server.connections.elementAt(server.connections.indexOf(this)));
                                     if(d.name==null)
                                       {      d.name=line;     }
                                     else  if(d.name!=null)     //有人在一个窗口中换用另一个名字登陆
                                        {  server.broadcast("QUIT"+this.name);    //写入QUIT+PEOPLE+名字+[性别]信息
                                            d.name=line;                                                           //使原来的人离开聊天室
                                         }                                 	  	  
                                       }
                                  catch(ArrayIndexOutOfBoundsException e)    {  }
                                  catch(NullPointerException  e)    {   }
                                  finally     {   server.broadcast(line);    }       //向聊天室所有人员发送有人进入聊天室信息  
                                 }
                     
                       //线路信息前端为QUIT表明有人离开了聊天室          
                         else if(line.startsWith("QUIT"))
                                {  server.broadcast("QUIT"+this.name);
                                    server.connections.removeElement(this);
                                    this.in.close();  this.out.close(); this.firstout.close();  //关闭输入输出流
                                    this.client.close();   this.yield();
                                 }       
                       //线路信息前端为MSG表明接收到的是普通聊天话语信息  
                             else  if(line.startsWith("MSG"))
                               {  server.broadcast(line);     }
                               
                       //线路信息前端为“悄悄地对”表明接收到悄悄话      
                            else if(line.startsWith("悄悄地对"))
                                      {  this.out.writeUTF(line);    //悄悄话在自己的文本区显示
                                         server.broadcast1(line);        //悄悄话在对方文本区显示
                                       }   
                                  
                            //线路信息前端为newlist表明得到客户端刷新列表的请求
                                 else if(line.startsWith("newlist"))
                                   {  try 
                                        {  for(int i=0;i<server.connections.size();i++)
                                             {  firstthread  c=(firstthread)(server.connections.elementAt(i));
                                                 if(c.name!=null)
                                                   {  try 
                                                            {       firstout.writeUTF(c.name+"*");         }  //name格式为：PEOPLE+名字+[性别]
                                                       catch(IOException e)    {   } 
                                                   }
                                             }
                                         }
                                         catch(ArrayIndexOutOfBoundsException  e)     {     }
                                         catch(NullPointerException  e)    {  }
                                   }
                          }
                     }
             catch(IOException e)
                  {      server.connections.removeElement(this);     } 
             catch(NullPointerException  e)  
                  {      server.connections.removeElement(this);     }
           }
   }
                                  	
                                 
                  
                 
               
        
       
         
    