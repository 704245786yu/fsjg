package bizTest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**导入log4j-core-2.5.jar和log4j-api-2.5.jar
 * */
public class log4j2Test {

	//getLogger()的参数指定的是这个logger的名称，这个名称在配置文件里是有需要的。
	private static Logger logger = LogManager.getLogger(log4j2Test.class);
	
	/**测试无配置文件的执行效果
	 * log4j2有一个默认的设置，它的日志级别是ERROR，且输出控制台。
	 * */
	@Test
	public void test1(){
		logger.entry();//trace级别的信息，单独列出来是希望你在某个方法或者程序逻辑开始的时候调用，和logger.trace("entry")基本一个意思
        logger.error("Did it again!");//error级别的信息，参数就是你输出的信息
        logger.info("我是info信息");//info级别的信息
        logger.debug("我是debug信息");
        logger.warn("我是warn信息");
        logger.fatal("我是fatal信息");
        logger.log(Level.DEBUG, "我是debug信息");//这个就是制定Level类型的调用：谁闲着没事调用这个，也不一定哦！
        logger.exit();//和entry()对应的结束方法，和logger.trace("exit");一个意思
	}
}
