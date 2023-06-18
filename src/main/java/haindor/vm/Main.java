package haindor.vm;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
//         读取字节码16进制数据流
        byte[] bytes = FileUtil.readBytes("D:\\java_project\\JavaClassTest\\out\\production\\JavaClassTest\\Main.class");
//        char[] chars = HexUtil.encodeHex(bytes);
//        for (char aChar : chars) {
//            System.out.print(aChar);
//        }
        log.info("hello");
    }

}