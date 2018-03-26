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
            //目录列表器
            File path = new File(".");
            String[] list;
            if (args.length == 0) {
                list = path.list();
            } else {
                list = path.list(new DirFilter(args[0]));
            }
            Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
            for (String dirItem : list)
                System.out.println(dirItem);
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
