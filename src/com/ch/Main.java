package com.ch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ch.core.CoreEngine;
import com.ch.core.Game;
import com.ch.core.scene.Scene;

public class Main {

	public static void main(String[] args) {

//        int x;
//        for (x = 20; x --> 0;) {
//            System.out.println(x);
//        }

//		copyResFromGD("3D Game Engine", getProjectPath());

        Game mainGame = new Game();
        Scene s = new TestGame();
        mainGame.setCurrentScene(s);
		CoreEngine engine = new CoreEngine(mainGame);
		String usr = System.getProperty("user.name");
//		if (usr.toLowerCase().contains("tim"))
			engine.createWindow(640, 480, "3D Game Engine");
//		else
//			engine.createWindow(1920, 1080, "3D Game Engine");
		engine.start();

	}

	public static final void copyResFromGD(String GDprojectName, String eclipsePath_ProjectLocation) {

		String homePath = System.getProperty("user.home");
		String gDrivePath = homePath + "/Google Drive";

		copyFolderTo(gDrivePath + "/" + GDprojectName + "/res", eclipsePath_ProjectLocation + "/res");

	}

	public static final void copyFolderTo(String location, String destination) {

		File original = new File(location);
		File result = new File(destination);
		
		try {
			copyFiles(original, result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void copyFiles(File src, File dest) throws IOException {

		if (src.isDirectory()) {

			if (!dest.exists()) {
				dest.mkdir();
			}

			String files[] = src.list();

			for (String file : files) {
				File srcFile = new File(src, file);
				File destFile = new File(dest.getPath() + "/" + file);
				copyFiles(srcFile, destFile);
			}

		} else {

			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;

			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();
		}
	}
	
	public static final String getProjectPath() {

		return new File("").getAbsolutePath();
		
	}

}
