package com.example;

import java.io.*;
import java.net.*;

public class ServerStr 
{
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi()
    {
        try
        {
            System.out.println("server partito in esecuzione...");
            server =new ServerSocket(6789);
            client =server.accept();
            server.close();
            inDalClient=new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient=new DataOutputStream(client.getOutputStream());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
    }

    public void comunica()
    {
        try
        {
            int n=(int)(Math.random()*100)+1;
            System.out.println(n);
            while(true)
            {
                stringaRicevuta=inDalClient.readLine();
                if(Integer.parseInt(stringaRicevuta)<n)
                {
                    outVersoClient.writeBytes("1"+'\n');
                }
                else if(Integer.parseInt(stringaRicevuta)>n)
                {
                    outVersoClient.writeBytes("2"+'\n');
                }
                else
                 break;
            }
            
            
            outVersoClient.writeBytes("3"+'\n');
            System.out.println("fine");
            client.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
