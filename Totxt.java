import java.io.*;

public class Totxt {
    private static String fileTag="###File###";
    public static void main(String args []){
        if(args.length<3) {
            System.out.println("参数个数有误:文件夹 操作1:filetotext;2:releasetofile savePath");
            System.exit(-1);
        }
        String filepath=args[0];
        String op=args[1];
        String savePath=args[2];
        if(op.equals("1")){
            getFiles(filepath,filepath,savePath);
        }else if(op.equals("2")) {
            relaseJava(filepath, savePath);
        }else {
            System.out.println("参数错误!");
        }
    }

    private static void getFiles(String filePath,String rootpath,String savePath) {
        File root = new File(filePath);
        File[] files = root.listFiles();
        String tmpstr="";
        for(File file:files){
            if(file.isDirectory()){
      /*
       * 递归调用
       */       System.out.println(filePath.replace(rootpath,""));
                getFiles(file.getAbsolutePath(),rootpath,savePath);
            }else {
                System.out.println(file.getPath().replace(rootpath, ""));
                File bf = new File(savePath);
                FileInputStream is = null;
                FileOutputStream os = null;
                if (!bf.exists()) {
                    try {
                        bf.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    is = new FileInputStream(file);
                    os = new FileOutputStream(bf,true);

                    byte b[] = new byte[1024];
                    int len;
                    try {
                        tmpstr=file.getPath().replace(rootpath.replace("/","\\"), "").replace("\\","/");
                        os.write(("\r\n"+fileTag+tmpstr+"\r\n").getBytes());
                        len = is.read(b);
                        while (len != -1) {
                            os.write(b, 0, len);
                            len = is.read(b);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (is != null) is.close();
                        if (os != null) os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void relaseJava(String filepath,String spath){
        File file=new File(filepath);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));
            String str=null;
            // write string to file
            FileWriter writer = null;
            BufferedWriter bw=null;
            while ((str=br.readLine())!=null){
                if(str.contains(fileTag)) {
                    if (bw != null) {
                        bw.close();
                        writer.close();
                    }
                    String tmp[]=str.replace(fileTag, "").split("/");
                    String savepath = spath;
                    File f=new File(savepath);
                    if(!f.exists())
                        f.mkdir();

                    for (int i=0;i<tmp.length-2;i++) {
                        savepath=savepath+"/"+tmp[i];
                        f=new File(savepath);
                        if(!f.exists())
                            f.mkdir();
                    }
                    savepath+="/"+tmp[tmp.length-1];
                    // write string to file
                    writer = new FileWriter(savepath);
                    bw = new BufferedWriter(writer);
                }else if(bw!=null) {
                    bw.write(str.toString());
                }
            }

        }catch (Exception e){

        }
    }
}
