package com.example;

import java.io.*;
import java.net.*;

public class ServerStr 
{
    ServerSocket server = null;
    Socket client = null;
    int stringaRicevuta = 0;
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
            outVersoClient.writeBytes("x"+'\n');
            stringaRicevuta=Integer.parseInt(inDalClient.readLine());
            while(stringaRicevuta!=n)
            {
                if(stringaRicevuta<n)
                {
                    outVersoClient.writeBytes("1");
                    stringaRicevuta=Integer.parseInt(inDalClient.readLine());
                }
                else
                {
                    outVersoClient.writeBytes("2");
                    stringaRicevuta=Integer.parseInt(inDalClient.readLine());
                }
            }
            outVersoClient.writeBytes("3"+'\n');
            client.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
