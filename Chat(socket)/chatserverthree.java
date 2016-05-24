
import  java.net.*;
import  java.io.*;
import  java.util.*;

public class chatserverthree   implements  Runnable
 {  public  static final int  PORT=1252;   
     protected ServerSocket  listen;       //������������׽���listen
     static Vector connections;     //������connections�������������ӵĿͻ��߳��б�
     Thread  connect;   //������������߳�
     
     //�����������ʼ���߳�
     public chatserverthree()
      {  try   
            {     listen=new ServerSocket(PORT);        }  //ʹ�ñ���IP��ַ����һ��������
         catch(UnknownHostException e2)  {  System.err.println("erro:"+e2);   }
         catch(IOException  e)
            {  System.err.println("erro:"+e);     System.exit(1);       }
          connections=new  Vector(1000);   
          connect=new  Thread(this);   
          connect.start();       //���������߳�����
      }
      
      //������main()��ʼ��   
     public  static void main(String args[])
         {  new  chatserverthree();  
            System.out.println("Chat Server is starting!......");   }
      
      //�������߳�connect����run����
     public  void run()
      {  try
           {  while(true)   //ʼ�ռ�����������˿ڵ���Ϣ
               {   Socket  client=listen.accept();
                    firstthread   f=new firstthread(this,  client);   //Ϊÿһ���˷ֱ�����һ���ͻ����߳�
                    f.setPriority(Thread.MIN_PRIORITY);  
                    f.start();     //�ͻ����߳�����
                    connections.addElement(f);    //���ͻ����̼߳��������б���
                }
           }
          catch(IOException  e)
             {  System.err.println("Erro:"+e);
                 System.exit(1);
              }
        }
       
      //��������������Ա������ͨ������Ϣ  
      public void  broadcast(String  msg)
        {   int i;    firstthread   you;
            for(i=0;i<connections.size();i++)
             {  you=(firstthread)connections.elementAt(i);
                 try
                   {      you.out.writeUTF(msg);      }
                 catch(IOException  e)    {    }
              }
          }
          
        // �������Ļ������ض���Ա�������Ļ�
         public  void broadcast1(String  msg)
           {   int i;     String   s1,s2,s3;
               firstthread  you;     s1=new  String("PEOPLE");
               s2=new  String(msg.substring(4));  //���ĵض�Ϊ4���ַ�
               s3=s1.concat(s2);
               for(i=0;i<connections.size();i++)
                 {   you=(firstthread)connections.elementAt(i);
                     if(s3.startsWith(you.name)) // name��ʽΪ��PEOPLE+����+[�Ա�]
                       {   try
                             {       you.out.writeUTF(msg);         }
                           catch(IOException  e)    {   } 
                        }
                   }
             }
  }
 
 //�ͻ����߳���firstthread 
 class firstthread  extends Thread
   {   protected  Socket client;   
       String  line,  name;        //line��ȡ���Կͻ��˵���Ϣ��     name��ʽΪ��PEOPLE+����+[�Ա�]
       protected  DataOutputStream  firstout,  out;   // �����������������
       protected  chatserverthree server;
       protected  DataInputStream   in;     // ������������������
       
       //firstthread��ʼ���߳�
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
                     {  System.err.println("������Ŷ:"+e);
                         return;
                      } 
               }
              if(this.client==null)
                 {     server.broadcast("QUIT"+this.name);   this.name=null;      }
          }
        public void run()
           {   try
                 {   //�ͻ����̳߳�ʼ�����ж�ȡ�������˵�������������Ա�б���Ϣ
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
                  
                  //�ͻ����߳�ʼ���ڼ����Ĳ�������
                  try  
                     {   while(true)
                          {  line=in.readUTF();    // line��ȡ���Կͻ�����·����Ϣ
                          
                       //��·��Ϣǰ��ΪPEOPLE���������˽�����������
                              if(line.startsWith("PEOPLE")) 
                               {   try
                                       	//��ȡ��ǰ������߳�
           {   firstthread  d=(firstthread)(server.connections.elementAt(server.connections.indexOf(this)));
                                     if(d.name==null)
                                       {      d.name=line;     }
                                     else  if(d.name!=null)     //������һ�������л�����һ�����ֵ�½
                                        {  server.broadcast("QUIT"+this.name);    //д��QUIT+PEOPLE+����+[�Ա�]��Ϣ
                                            d.name=line;                                                           //ʹԭ�������뿪������
                                         }                                 	  	  
                                       }
                                  catch(ArrayIndexOutOfBoundsException e)    {  }
                                  catch(NullPointerException  e)    {   }
                                  finally     {   server.broadcast(line);    }       //��������������Ա�������˽�����������Ϣ  
                                 }
                     
                       //��·��Ϣǰ��ΪQUIT���������뿪��������          
                         else if(line.startsWith("QUIT"))
                                {  server.broadcast("QUIT"+this.name);
                                    server.connections.removeElement(this);
                                    this.in.close();  this.out.close(); this.firstout.close();  //�ر����������
                                    this.client.close();   this.yield();
                                 }       
                       //��·��Ϣǰ��ΪMSG�������յ�������ͨ���컰����Ϣ  
                             else  if(line.startsWith("MSG"))
                               {  server.broadcast(line);     }
                               
                       //��·��Ϣǰ��Ϊ�����ĵضԡ��������յ����Ļ�      
                            else if(line.startsWith("���ĵض�"))
                                      {  this.out.writeUTF(line);    //���Ļ����Լ����ı�����ʾ
                                         server.broadcast1(line);        //���Ļ��ڶԷ��ı�����ʾ
                                       }   
                                  
                            //��·��Ϣǰ��Ϊnewlist�����õ��ͻ���ˢ���б������
                                 else if(line.startsWith("newlist"))
                                   {  try 
                                        {  for(int i=0;i<server.connections.size();i++)
                                             {  firstthread  c=(firstthread)(server.connections.elementAt(i));
                                                 if(c.name!=null)
                                                   {  try 
                                                            {       firstout.writeUTF(c.name+"*");         }  //name��ʽΪ��PEOPLE+����+[�Ա�]
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
                                  	
                                 
                  
                 
               
        
       
         
    