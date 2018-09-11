package mine.opengl;

import android.content.Context;
import android.opengl.GLES20;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.IntBuffer;

/**
 * Created by Administrator on 2018/8/20.
 */
public class TriangleShader {
    private int programID = -1;
    private int positionID, colorID;

    private String vertexShaderCode;
    private String fragmentShaderCode;

    public TriangleShader(Context context) {
        vertexShaderCode = readShader(context.getResources().openRawResource(R.raw.triangle_vs));
        System.out.println(vertexShaderCode);
        fragmentShaderCode = readShader(context.getResources().openRawResource(R.raw.triangle_fs));
        System.out.println(fragmentShaderCode);
    }

    public int getProgramID() {
        return (programID != -1) ? programID : createShader();
    }

    private String readShader(InputStream inputStream) {

        try {
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            final StringBuilder builder = new StringBuilder(128);
            char[] buffer = new char[8192];
            int len;
            while ((len = reader.read(buffer)) > 0) {
                builder.append(buffer, 0, len);
            }
            return builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private int createShader() {
        System.out.println("~~createShader~~");

        programID = GLES20.glCreateProgram();

        int VSShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(VSShader, vertexShaderCode);
        GLES20.glCompileShader(VSShader);

        int FSShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(FSShader, fragmentShaderCode);
        GLES20.glCompileShader(FSShader);


        //add the fragment shader to program
        GLES20.glAttachShader(programID, VSShader);
        GLES20.glAttachShader(programID, FSShader);
        GLES20.glLinkProgram(programID);


        IntBuffer linkStatus = IntBuffer.allocate(1);
        GLES20.glGetProgramiv(programID, GLES20.GL_LINK_STATUS, linkStatus);
        if (linkStatus.get(0) == GLES20.GL_FALSE) {
            String linkError = GLES20.glGetProgramInfoLog(programID);
            System.out.println("[linkError]" + linkError);
        }

        int[] validateStatus = new int[1];
        GLES20.glGetProgramiv(programID, GLES20.GL_VALIDATE_STATUS, validateStatus, 0);
        if (validateStatus[0] == GLES20.GL_FALSE) {

            String validateError = GLES20.glGetProgramInfoLog(programID);
            System.out.println("[validateError]" + validateError);
        }

        return programID;
    }
}
