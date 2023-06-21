package haidnor.vm;

import haidnor.vm.core.JavaNativeInterface;
import haidnor.vm.runtime.JvmThread;
import haidnor.vm.util.JavaClassUtil;
import haidnor.vm.util.ThreadHolder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.commons.cli.*;

@Slf4j
public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        Option jarOption = new Option("jar", true, "运行 jar 程序");
        Option classOption = new Option("class", true, "运行 .class 字节码文件");

        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(jarOption).addOption(classOption);

        Options options = new Options();
        options.addOptionGroup(optionGroup);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("jar")) {
            String path = cmd.getOptionValue("jar");
            // TODO
        }
        if (cmd.hasOption("class")) {
            String path = cmd.getOptionValue("class");

            ClassParser classParser = new ClassParser(path);
            JavaClass javaClass = classParser.parse();

            Method mainMethod = JavaClassUtil.getMainMethod(javaClass);
            if (mainMethod == null) {
                throw new Error("无法找到 main 方法");
            }

            JvmThread mainThread = new JvmThread();
            ThreadHolder.set(mainThread);

            // 执行main方法
            JavaNativeInterface.callStaticMethod(mainMethod);
        }
    }

}