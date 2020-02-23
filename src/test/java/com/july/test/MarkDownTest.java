package com.july.test;

import com.july.doc.JulyDoc;
import com.july.doc.config.DocLanguage;
import com.july.doc.entity.ApiConfig;
import com.july.doc.entity.SourceCodePath;
import com.july.doc.showdoc.JulyShowDocUtil;
import com.july.doc.showdoc.ShowDoc;
import com.july.doc.showdoc.ShowDocModel;

import com.july.test.util.ToolUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 自动生成接口文档
 * @author zqk
 * @since 2020/01/06
 */
public class MarkDownTest {

    /**
     * showdoc项目唯一标识
     */
    private static final String app_key = "425bf7c07088862a53fb1cc595e7d0da1941420507";
    /**
     * showdoc项目唯一token
     */
    private static final String app_token = "de9850763fbd32f3d90e84213d23a1881043480671";
    /**
     * show doc部署地址
     */
    private static final String showdoc_url = "http://api-doc.sjhh-inner.com";

    /**
     * 生成一个控制器或者控制器里面的一个API
     * @throws Exception
     * @author zqk
     * @since 2020/01/06
     */
    @Test
    public void generateOneApi() throws Exception {
        ApiConfig config = new ApiConfig();
        //设置接口文档生成的语言格式，默认为：中文
        config.setLanguage(DocLanguage.CHINESE);
        //接口地址，不设置默认为：http://localhost:8080
        config.setServerUrl("htthp://localhost:8080");
        //指定控制器
        config.setControllerName("com.july.test.controller.UserInfoController");
        //指定控制器里面的那个接口
        config.setMethodName("getUserInfo");
        //show doc错误代码列表页面地址
        config.setErrorUrl("https://www.showdoc.cc/zengxueqi?page_id=3628979420871346");
        //不指定SourcePaths默认加载代码为项目src/main/java下的
        config.setSourceCodePath(SourceCodePath.path().setPath("src/main/java"));
        //是否生成本地markdown文件，为false时不生成本地文件，把文件上传到showdoc上
        config.setIsOpenLocal(true);
        //放在showdoc的哪个目录
        config.setFolder("测试/测试");
        //生成本地接口文档的保存地址
        config.setOutPath("./target/doc");
        /*config.setRequestHeaders(ApiReqHeader.header().setName("access_token").setType("string").setDesc("Basic auth credentials"),
                ApiReqHeader.header().setName("userinfo").setType("string").setDesc("school_1_285"));*/

        //获取所有的Markdown文件
        List<ShowDocModel> showDocModels = JulyDoc.generateOneApi(config);
        //上传markdown文档至show doc
        JulyShowDocUtil.doPost(new ShowDoc(config.getIsOpenLocal(), showdoc_url, app_key, app_token, showDocModels));
    }

    /**
     * 生成一个控制器或者控制器里面的一个API
     * @throws Exception
     * @author zqk
     * @since 2020/01/06
     */
    @Test
    public void generateOneHtmlApi() throws Exception {
        ApiConfig config = new ApiConfig();
        //设置接口文档生成的语言格式，默认为：中文
        config.setLanguage(DocLanguage.CHINESE);
        //接口地址，不设置默认为：http://localhost:8080
        config.setServerUrl("htthp://localhost:8080");
        //指定控制器
        config.setControllerName("com.july.test.controller.UserInfoController");
        //指定控制器里面的那个接口
        config.setMethodName("getUserInfo");
        //show doc错误代码列表页面地址
        config.setErrorUrl("http://api-doc.sjhh-inner.com/web/#/73?page_id=2872");
        //不指定SourcePaths默认加载代码为项目src/main/java下的
        config.setSourceCodePath(SourceCodePath.path().setPath("src/main/java"));
        //是否生成本地markdown文件，为false时不生成本地文件，把文件上传到showdoc上
        config.setIsOpenLocal(true);
        //放在showdoc的哪个目录
        config.setFolder("测试/测试");
        //生成本地接口文档的保存地址
        config.setOutPath("./target/doc");
        /*config.setRequestHeaders(ApiReqHeader.header().setName("access_token").setType("string").setDesc("Basic auth credentials"),
                ApiReqHeader.header().setName("userinfo").setType("string").setDesc("school_1_285"));*/

        JulyDoc.generateOneHtmlApi(config);
    }

    @Test
    public void generatePassword(){
        ToolUtil.entryptPassword("123456");
    }

    public void generatePostmanJson(){
        //Postm
    }

}
