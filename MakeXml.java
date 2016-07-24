package com.leapsea.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 *
 * blog:blog.leapsea.com
 * Created by teemo on 2016/3/1.
 * update 2016.07.12  根据zol.com.cn热门手机排行榜整理
 * http://detail.zol.com.cn/cell_phone_advSearch/subcate57_1_s6472-s6132-s5359-s5329-s1398-s6500-s6502-s6106_1_1_0_1.html?#showc
 */
public class MakeXml {

    private final static String rootPath = "D:/workSpace/android/Tourkaa/app/src/main/res";
    private final static String VALUE_TEMPLATE = "values-{0}x{1}";

    private final static float baseW = 320f;
    private final static float baseH = 480f;

    private final static String wTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n";
    private final static String hTemplate = "<dimen name=\"y{0}\">{1}px</dimen>\n";

    public static void main(String[] args) {

        MakeXml makeXml = new MakeXml();

        makeXml.generateXmlFile(320, 480);
        makeXml.generateXmlFile(480, 800);    // 三星W2014
        makeXml.generateXmlFile(480, 854);    // OPPO A31
        makeXml.generateXmlFile(540, 960);    // vivo Y51A,OPPO A33,vivo Y31A
        makeXml.generateXmlFile(600, 1024);
        makeXml.generateXmlFile(720, 1184);
        makeXml.generateXmlFile(720, 1196);
        makeXml.generateXmlFile(720, 1280);   // OPPO A59,OPPO A37,华为畅享5S,vivo V3,三星W2015,OPPO A53,华为畅享5,荣耀4A,vivo X5L,荣耀畅玩4C,荣耀畅玩4X,三星GALAXY On5,金立S6,华为P8 Lite,华为Ascend P6,荣耀畅玩5A,中兴Blade A2,华为畅享5S,三星GALAXY On7,三星GALAXY A5,华硕电神5000,华为Ascend G7,金立金钢,小米红米Note,大神F2,联想乐檬K3,小米红米2,vivo Y27
        makeXml.generateXmlFile(768, 1024);
        makeXml.generateXmlFile(768, 1280);   // 三星W2016
        makeXml.generateXmlFile(800, 1280);
        //makeXml.generateXmlFile(1080, 1776);  // 1080*1920出现导航栏的尺寸。可以使用1080*1920的xml  
        makeXml.generateXmlFile(1080, 1812);
        makeXml.generateXmlFile(1080, 1920);  // 三星GALAXY C5,vivo X7,华为Mate 8,OPPO R9,华为P9,乐视乐2,魅族魅蓝Note 3,一加手机3,vivo X6S,vivo X6,三星GALAXY C7,荣耀畅玩5X,OPPO R9 Plus,nubia Z11,华为P9,华为P8,荣耀7,联想ZUK Z2,360 手机N4,三星GALAXY A9,华为Mate 8,乐视乐1S,华为P9 Plus,荣耀畅玩5C,小米红米Note3,魅族MX5,nubia Z11,vivo V3Max,小米4,华为G9,OPPO R7,8848 钛金手机,荣耀畅玩5C,荣耀6,华为Mate 7,vivo X7 Plus,华为麦芒4,荣耀6 Plus,三星GALAXY A8,三星2016版GALAXY A9,三星GALAXY Note 3,nubia Z11 Max,nubia Z11 mini,小米Note,荣耀畅玩5X,魅族魅蓝Note 3,三星GALAXY S5,金立S8,华为Ascend P7,美图M6,荣耀7i,华为Mate S,金立M5 Plus,vivo Xshot,魅族MX6,三星GALAXY S4,TCL 750,vivo X6Plus,荣耀7,荣耀V8,华为G9,vivo X5Pro,小米3,乐视乐2 Pro,华为P9 Plus,OPPO R7s,金立S6 Pro,华为Ascend P7,小米4C,OPPO R7 Plus,三星GALAXY A7,荣耀8,索尼Xperia Z5,乐视乐2 Pro,华为P8max,三星2016版GALAXY A7,乐视乐1,HTC One M8,vivo X6SPlus,魅族魅蓝Note 2,索尼Xperia Z3,vivo X5Max L,联想ZUK Z1,OPPO R7 Plus高配版,三星GALAXY J7,华为G7 Plus,锤子科技Smartisan T2,三星2016版GALAXY A5,锤子科技坚果手机,酷派锋尚MAX,华为Mate 7
        makeXml.generateXmlFile(1152, 1920);  // 魅族MX4
        makeXml.generateXmlFile(1200, 1920);
        makeXml.generateXmlFile(1440, 2560);  // 三星GALAXY S7,vivo Xplay5,中兴天机7,三星GALAXY S7 Edge,荣耀V8,乐视乐Max 2,三星GALAXY S6,vivo Xplay5,三星GALAXY Note 4,三星GALAXY Note 5,三星GALAXY S6 Edge,黑莓Priv,三星GALAXY S6 Edge+,三星GALAXY Note 7,HTC 10,摩托罗拉Moto X 极,vivo Xplay3S,LG G5,乐视乐Max,LG G5 SE,三星GALAXY S6,摩托罗拉Moto X Pro,HTC 10,乐视乐Max 2
        makeXml.generateXmlFile(1536, 2560);  // 魅族MX4 Pro
        makeXml.generateXmlFile(1600, 2560);  // OPPO Find 9
        makeXml.generateXmlFile(2160, 3840);  // 索尼Xperia Z5 Premium
    }

    public void generateXmlFile(int w, int h) {
        StringBuffer sbForWidth = new StringBuffer();
        sbForWidth.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sbForWidth.append("<resources>");
        float cellw = w * 1.0f / baseW;

        System.out.println("width : " + w + "," + baseW + "," + cellw);
        for (int i = 1; i < baseW; i++) {
            sbForWidth.append(wTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellw * i) + ""));
        }
        sbForWidth.append(wTemplate.replace("{0}", baseW + "").replace("{1}",
                w + ""));
        sbForWidth.append("</resources>");

        StringBuffer sbForHeight = new StringBuffer();
        sbForHeight.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sbForHeight.append("<resources>");
        float cellh = h * 1.0f / baseH;
        System.out.println("height : " + h + "," + baseH + "," + cellh);
        for (int i = 1; i < baseH; i++) {
            sbForHeight.append(hTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellh * i) + ""));
        }
        sbForHeight.append(hTemplate.replace("{0}", baseH + "").replace("{1}",
                h + ""));
        sbForHeight.append("</resources>");

        File fileDir = new File(rootPath + File.separator
                + VALUE_TEMPLATE.replace("{0}", h + "")//
                .replace("{1}", w + ""));
        fileDir.mkdir();

        File layxFile = new File(fileDir.getAbsolutePath(), "lay_x.xml");
        File layyFile = new File(fileDir.getAbsolutePath(), "lay_y.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sbForWidth.toString());
            pw.close();
            pw = new PrintWriter(new FileOutputStream(layyFile));
            pw.print(sbForHeight.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }
}
