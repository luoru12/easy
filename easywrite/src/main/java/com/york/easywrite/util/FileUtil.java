package com.york.easywrite.util;

import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

  private static final String NUMBERCHAR = "0123456789";


  public static void saveFile(String fileStr, String filePath) {
    File file = new File(filePath);
    File parentFile = file.getParentFile();
    if (!parentFile.exists()) {
      parentFile.mkdirs();
    }
    FileWriter fw = null;
    boolean var14;
    try {
      file.createNewFile();
      fw = new FileWriter(filePath);
      fw.write(fileStr);
      fw.close();
    } catch (IOException var24) {
      var24.printStackTrace();
    } finally {
      if (fw != null) {
        try {
          fw.close();
        } catch (IOException var23) {
          var23.printStackTrace();
        }
      }

    }

  }

  /**
   * 将字节流转换成文件
   *
   * @param filename
   * @param data
   * @throws Exception
   */
  public static void saveFile(byte[] data, String filePath) throws Exception {
    if (data != null) {
      File file = new File(filePath);
      File parentFile = file.getParentFile();
      if (!parentFile.exists()) {
        parentFile.mkdirs();
      }
      FileOutputStream fos = new FileOutputStream(file);
      fos.write(data, 0, data.length);
      fos.flush();
      fos.close();
    }
  }

  public static void deleteFile(String filePath) {
    File file = new File(filePath);
    if (file.exists() && file.isFile()) {
      file.delete();
    }
  }

  public static void delDir(String path) {
    File dir = new File(path);
    if (dir.exists()) {
      File[] tmp = dir.listFiles();
      for (int i = 0; i < tmp.length; i++) {
        if (tmp[i].isDirectory()) {
          delDir(path + "/" + tmp[i].getName());
        } else {
          tmp[i].delete();
        }
      }
      dir.delete();
    }
  }


  /**
   * 字节数组转16进制
   *
   * @param bytes 需要转换的byte数组
   * @return 转换后的Hex字符串
   */
  public static String bytesToHex(byte[] bytes) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < bytes.length; i++) {
      String hex = Integer.toHexString(bytes[i] & 0xFF);
      if (hex.length() < 2) {
        sb.append(0);
      }
      sb.append(hex);
    }
    return sb.toString();
  }

  /**
   * @param imgBase64Str
   * @param imgFilePath  完整图片路径
   * @return
   * @throws IOException
   */
  public static String ldmtranstojpg(String imgBase64Str, String imgFilePath) throws IOException {
    if (imgBase64Str == null) {
      return null;
    } else {
      BASE64Decoder decoder = new BASE64Decoder();
      byte[] b = decoder.decodeBuffer(imgBase64Str);

      for (int i = 0; i < b.length; ++i) {
        if (b[i] < 0) {
          b[i] = (byte) (b[i] + 256);
        }
      }
      File filePath = new File(imgFilePath);
      File imgDir = new File(filePath.getParent());
      if (!imgDir.exists()) {
        imgDir.mkdirs();
      }

      try {
        ByteArrayInputStream in = new ByteArrayInputStream(b);
        BufferedImage bufferedImage = ImageIO.read(in);
        BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
            bufferedImage.getHeight(), 1);
        newBufferedImage.createGraphics()
            .drawImage(bufferedImage, 0, 0, Color.WHITE, (ImageObserver) null);
        ImageIO.write(newBufferedImage, "jpg", new File(imgFilePath));
        System.out.println("成功将格式图片转换为jpg格式并保存");
      } catch (IOException var10) {
        var10.printStackTrace();
      }
      return filePath.getName();
    }
  }

  public static String encodeImageToBase64Heard(String imageUrlPath) {
    return "data:image/jpg;base64," + encodeImageToBase64(imageUrlPath);
  }

  /**
   * 将网络图片url编码为base64
   *
   * @param imageUrlPath
   * @return
   * @throws
   */
  public static String encodeImageToBase64(String imageUrlPath) {
    //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
    // System.out.println("图片的路径为:" + url.toString());
    //打开链接
    HttpURLConnection conn = null;
    InputStream inStream = null;
    try {
      URL url = new URL(imageUrlPath);
      conn = (HttpURLConnection) url.openConnection();
      //设置请求方式为"GET"
      conn.setRequestMethod("GET");
      //超时响应时间为5秒
      conn.setConnectTimeout(5 * 1000);
      //通过输入流获取图片数据
      inStream = conn.getInputStream();
      //得到图片的二进制数据，以二进制封装得到数据，具有通用性
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      //创建一个Buffer字符串
      byte[] buffer = new byte[1024];
      //每次读取的字符串长度，如果为-1，代表全部读取完毕
      int len = 0;
      //使用一个输入流从buffer里把数据读取出来
      while ((len = inStream.read(buffer)) != -1) {
        //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
        outStream.write(buffer, 0, len);
      }
      //关闭输入流
      byte[] data = outStream.toByteArray();
      //对字节数组Base64编码
      BASE64Encoder encoder = new BASE64Encoder();
      //返回Base64编码过的字节数组字符串
      String base64 = encoder.encode(data);
      return base64.replaceAll("\r\n", "");
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    } finally {
      if (inStream != null) {
        try {
          inStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }


  public static byte[] readImageBytes(String path) {
    byte[] fileByte = null;
    try {
      File file = new File(path);
      fileByte = Files.readAllBytes(file.toPath());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return fileByte;
  }


  /**
   * 图片转为base64
   *
   * @param path
   * @return
   */
  public static String imageToBase64(String path) {
    InputStream in = null;
    byte[] bytes = null;
    try {
      File file = new File(path);
      if (!file.exists()) {
        return null;
      }
      in = new FileInputStream(file);
      bytes = new byte[in.available()];
      in.read(bytes);
      BASE64Encoder encoder = new BASE64Encoder();
      return encoder.encode(bytes);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /*
   * 获取项目绝对路径
   */
  public static String getAbsolutePath() {

    String filePath = "";
    try {
      File path = new File(ResourceUtils.getURL("classpath:").getPath());
      if (!path.exists()) {
        path = new File("");
      }
      filePath = path.getAbsolutePath();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return filePath;
  }

  /*
   * 读取txt文件内容
   */
  public static String readTxtFile(File txtFile) {
    StringBuilder result = new StringBuilder();
    try {
      BufferedReader br = new BufferedReader(new FileReader(txtFile));// 构造一个BufferedReader类来读取文件
      String s = null;
      while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
        result.append(System.lineSeparator() + s);
      }
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result.toString();
  }

  public static String getBirthDayByIdCard(String idCard) {
    if (StringUtils.isEmpty(idCard) || idCard.length() != 18) {
      return "";
    }
    String year = idCard.substring(6, 10);
    String moth = idCard.substring(10, 12);
    String day = idCard.substring(12, 14);
    String birthDay = year + "-" + moth + "-" + day;
    return birthDay;
  }


  /**
   * 字节数组到String的转换.
   */
  public static String bytesToString(byte[] str) {
    String keyword = null;
    try {
      keyword = new String(str, "GBK");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return keyword;
  }


  /**
   * 验证是否是URL
   *
   * @param url
   * @return
   */
  public static boolean verifyUrl(String url) {

    // URL验证规则
    String regEx = "[a-zA-z]+://[^\\s]*";
    // 编译正则表达式
    Pattern pattern = Pattern.compile(regEx);
    // 忽略大小写的写法
    // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(url);
    // 字符串是否与正则表达式相匹配
    boolean rs = matcher.matches();
    return rs;
  }


  public static void main(String[] args) {
    String idcard = "456120199412158471";
    String birthDayByIdCard = getBirthDayByIdCard(idcard);
    System.out.println(birthDayByIdCard);
  }

  /**
   * 拼接文件地址
   *
   * @param path1
   * @param path2
   * @return
   */
  public static String combine(String path1, String path2) {
    File file1 = new File(path1);
    File file2 = new File(file1, path2);
    return file2.getPath();
  }

  /**
   * @param fileName
   * @return
   */
  public static String readToString(String fileName) {
    String encoding = "UTF-8";
    File file = new File(fileName);
    Long filelength = file.length();
    byte[] filecontent = new byte[filelength.intValue()];
    try {
      FileInputStream in = new FileInputStream(file);
      in.read(filecontent);
      in.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      return new String(filecontent, encoding);
    } catch (UnsupportedEncodingException e) {
      System.err.println("The OS does not support " + encoding);
      e.printStackTrace();
      return null;
    }
  }


  /**
   * 获取随机数
   *
   * @param length
   * @return
   */
  public static String generateUpperLowerString(int length) {
    StringBuffer sb = new StringBuffer();
    Random random = new Random();
    for (int i = 0; i < length; i++) {
      sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
    }
    return sb.toString();
  }

  public static String multipartFileToString(MultipartFile multipartFile) {
    try {
      Reader reader = new InputStreamReader(multipartFile.getInputStream(), StandardCharsets.UTF_8);
      BufferedReader bufferedReader = new BufferedReader(reader);
      StringBuilder stringBuilder = new StringBuilder();
      String lineStr;
      //逐行读取
      while (null != (lineStr = bufferedReader.readLine())) {
        stringBuilder.append(lineStr).append("\n");
      }
      return stringBuilder.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
