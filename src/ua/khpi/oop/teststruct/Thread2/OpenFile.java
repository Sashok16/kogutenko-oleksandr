package ua.khpi.oop.teststruct.Thread2;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class OpenFile implements Runnable
{
    private FileChannel _channel;
    private FileChannel _writeChannel;
    private int _startLocation;
    private int _size;

    public OpenFile(int loc, int sz, FileChannel chnl, FileChannel write)
    {
        _startLocation = loc;
        _size = sz;
        _channel = chnl;
        _writeChannel = write;
    }

    public void run()
    {
        try
        {
            System.out.println("Reading the channel: " + _startLocation + ":" + _size);
            ByteBuffer buff = ByteBuffer.allocate(_size);
            if (_startLocation == 0)
                Thread.sleep(100);
            _channel.read(buff, _startLocation);
            ByteBuffer wbuff = ByteBuffer.wrap(buff.array());
            int written = _writeChannel.write(wbuff, _startLocation);
            System.out.println("Read the channel: " + buff + ":" + new String(buff.array()) + ":Written:" + written);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
            throws Exception
    {
        FileOutputStream ostr = new FileOutputStream("OutBigFile.txt");
        FileInputStream str = new FileInputStream("BigFile.txt");
        String b = "Is this written";
        //ostr.write(b.getBytes());
        FileChannel chnl = str.getChannel();
        FileChannel write = ostr.getChannel();
        ByteBuffer buff = ByteBuffer.wrap(b.getBytes());
        write.write(buff);
        Thread t1 = new Thread(new OpenFile(0, 20, chnl, write));
        Thread t2 = new Thread(new OpenFile(20, 20, chnl, write));
        Thread t3 = new Thread(new OpenFile(40, 20, chnl, write));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        write.force(false);
        str.close();
        ostr.close();
    }
}
