package Demo;

/**
 * Created by vip on 2018/4/10.
 */
public class StringFix {
    public static String stringFix(String in, String alter) {
        String out = in.replace("int", "FETCH_INT(info.").
            replace("char", "FETCH_STRING(info.").
            replace("double", "FETCH_DOUBLE(info.").
            replace("long", "FETCH_INT(info.").
            replace(" ", "").
            replace("   ", "").
            replaceAll("\t", "").
            replaceAll("\\[\\S*\\]", "").
            replace(";", ", rows[i++]);").
            replace("info", alter);
        return out;
    }


    public static String stringFix2( String in, String alter) {
        String out = in.replace("int", "%d").
            replace("char", "\'%s\'").
            replace("double", "%lf").
            replaceAll("\n", ", ").
            replace(" ", "").
            replaceAll("\t", "").
            replace(",", ", ");
        return out;
    }

    public static String stringFix3( String in, String alter) {
        String out = in.replaceAll("\\[\\S*\\]", "").

            replaceAll("\n", alter + ".").
            replace(";", ", ").
            replace(" ", "").
            replaceAll("\t", "");
//        .
//            replace(",", ", ");
        return out;
    }

    public static void main(String[] args) {
//        String in = " \tint\tmedia_type;\n" + "\tint\tcarrier_type;\n" + "\tint\tcharge_flag;\n" +
//                    "\tint\ttrading_flag;\n" + "\tint\tlife_cycle_flag;\n" +
//                    "\tint\towner_flag;\n" + "\tint\towner_change_flag;\n" + "\tint\twith_acc;\n" +
//                    "\tint\tflag;\n" + "\tchar\tname[MAX_NAME+1];\n" +
//                    "\tchar\tmemo[MAX_MEMO+1];\n" + "\tchar\tteller[MAX_TELLER+1];\n" +
//                    "\tchar\tmodify_time[15];";
//        String alter = "media";
//        String out = stringFix(in, alter);
//        System.out.println(out);

        String in2 = "        char   \n" + "        char   \n" + "        char   \n" +
                     "        int    \n" + "        int    \n" + "        char   \n" +
                     "        char   \n" + "        int    \n";
        String out2 = stringFix2(in2, "");
        System.out.println(out2);

        String in3 = " barcode[MAX_PAN+1];      \n" + " create_time[15];         \n" +
                     " exp_time[15];            \n" + " owner_type;              \n" +
                     " acc_type;                \n" + " owner[MAX_SYS_USER_ID+1];\n" +
                     " channels[MAX_CHANNELS+1];\n" + " status;                  ";
        String out3 = stringFix3(in3, "barcode");
        System.out.println(out3);
    }
}
