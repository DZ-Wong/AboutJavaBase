package T0308.JavaIO;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;


/**
 * 读取输入.
 */
public class ReadInput {
    /**
     * Scanner对象输入，所有数据可见。
     * 清空缓冲区两种方式：
     * 1.in.nextLine(); 2.新建一个Scanner对象。
     */
    public static void readInput() {
        //Scanner 对象并与System.in绑定，所有的输入都是可见的。
        Scanner in = new Scanner(System.in);
        System.out.println("What's your name? ");
        String name = in.nextLine();//允许空格
        String first = in.next();//读取单个单词
        String second = " ";
        if (in.hasNext()) {
            second = in.next();
        }

//        in.nextLine();//清空缓冲区 1
//        in = new Scanner(System.in);//清空缓冲区 2

        System.out.println("How old are you?");
        Integer age = in.nextInt();
        System.out.println("How much can you accept?");
        Double salery = in.nextDouble();

        String message = String.format("Hello %s(%s %s); you are %d and you can accept %,.4f",
                name, first, second, age, salery);
        System.out.printf("%tc \n", new Date() );
        System.out.printf("Hello %s(%s %s); you are %d and you can accept %,.4f",
                name, first, second, age, salery);

        String dir = System.getProperty("user.dir");
        System.out.println(dir);

    }

    /**
     * Console对象输入，针对密码，只能读一行.
     * 密码用字符串数组存储，用完密码后，清空或者覆盖数组元素。
     * 这个类是调用console的，需要在cmd窗口执行。
     */
    public static void readSecret() {
        Console console = System.console();
        String username = console.readLine("User name: ");
        char[] passwd = console.readPassword("PassWd: ");
        console.printf(String.valueOf(passwd));
        System.out.println("username is " + username + "passwd is " + passwd);
        Arrays.fill(passwd, '1');
        passwd = null;
        System.out.println("username is " + username + "passwd is " + passwd);
        console.printf(String.valueOf(passwd));
    }

    public static void main(String[] args) throws IOException{
//        readInput();
//        readSecret();
//        readFile();
        writeFile();
    }

    /**
     * 读入文本输入
     * 1.Scanner 对象读取
     * 2.BufferedReader 对象
     */
    public static void readFile() throws IOException{
        Scanner in = new Scanner(Paths.get("d:\\log\\spring.log"));
        System.out.println(in.nextLine());

        //没有读入数字的方法
        BufferedReader in2 = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("d:\\log\\spring.log"), "UTF-8"));
        String line;
        while ((line = in2.readLine()) != null) {
            System.out.println(line);
        }
    }

    /**
     * 文件写出。以文本格式写出数据。
     * 1.PrintWriter 对象
     * 注意调用close方法。
     */
    public static void writeFile() throws IOException {
        PrintWriter out = new PrintWriter("d:\\log\\log.txt");
        //等同于以下的
        PrintWriter out2 = new PrintWriter(new FileWriter("xxx"));

        out.println("hello world");//会默认加入以下结束符--
        System.getProperty("line.separator");



        out.close();//一定要关闭才能写进去。
    }

    /**
     * 流读写。面向单字节流。
     * InputStream read  和 OutputStream write 阻塞操作。
     * 1. read  需要用 available() 方法去判断；
     * 2. 完成读写要close；flush 将缓冲区数据输出。
     * 3.组合使用
     * 4.
     */
    public static void streamRW() throws IOException{
        InputStream in = null;
        OutputStream out = null;
        //FileInputStream 没有读入数值的方法；只有从文件中读取数据
        FileInputStream fileIn = new FileInputStream("log.txt");
        //DataInputStream 没有任何从文件中读取数据的方法，需要两者组合使用。
        DataInputStream din = new DataInputStream(fileIn);
        double s = din.readDouble();
        //默认情况下流不被缓冲区缓存。DataInputString 有带缓冲机制的read方法
        DataInputStream din2 = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("log.txt")));

    }

    /**
     * 面向Unicode字符的读写类。
     * Reader 和 Writer（两个字节）
     */
    public static void readerRW() throws IOException{
        Reader reader = null;
        Writer writer = null;
        //下面的假定主机系统所使用的默认字符编码方式。
        InputStreamReader in = new InputStreamReader(System.in);
        //也可以指定编码方式
        InputStreamReader in2 = new InputStreamReader(new FileInputStream(""), "ISO8859_5");

        //以二进制格式写出数据
        DataOutputStream dataOutputStream = null;

    }
}
