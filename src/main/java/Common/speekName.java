package Common;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * 文字转语音测试 jdk bin文件中需要导入jacob-1.17-M2-x64.dll
 *
 * @author zk
 * @date: 2019年6月25日 上午10:05:21
 */
public class speekName extends Thread{
    private String text;
    public speekName(String text){
        this.text=text;
    }
    public void run(){
        textToSpeech(text);
    }
    public static void textToSpeech(String text) {
        ActiveXComponent ax = null;
        try {
            ax = new ActiveXComponent("Sapi.SpVoice");

            // 运行时输出语音内容
            Dispatch spVoice = ax.getObject();
            // 音量 0-100
            ax.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            ax.setProperty("Rate", new Variant(-2));
            // 执行朗读
            Dispatch.call(spVoice, "Speak", new Variant(text));
            spVoice.safeRelease();
            ax.safeRelease();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}