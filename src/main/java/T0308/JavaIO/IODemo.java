package T0308.JavaIO;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by vip on 2018/3/26.
 */
public class IODemo {
        public static void main(String[] args) {
            //目录列表器1
            File path = new File(".");
            String[] list;
            if (args.length == 0) {
                list = path.list();
            } else {
                //1 创建新类
                list = path.list(new DirFilter(args[0]));
                //2 匿名内部类作为单独静态函数
                list = path.list(filter(args[0]));
                //3 作为list（）函数的匿名内部类
                list = path.list(new FilenameFilter() {
                    private Pattern pattern = Pattern.compile(args[0]);

                    @Override
                    public boolean accept(File dir, String name) {
                        return pattern.matcher(name).matches();
                    }
                });
            }
            Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
            for (String dirItem : list)
                System.out.println(dirItem);
        }

        //2匿名内部类
    //传向filter（）参数必须是final，在匿名内部类必须的，才能使用来自该类范围之外的对象

        public static FilenameFilter filter(final String regex) {
            return new FilenameFilter() {
                private Pattern pattern = Pattern.compile(regex);
                @Override
                public boolean accept(File dir, String name) {
                    return pattern.matcher(name).matches();
                }
            };
        }
}

class DirFilter implements FilenameFilter {
    private Pattern pattern;

    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
