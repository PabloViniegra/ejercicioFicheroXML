import java.io.*;
import java.util.ArrayList;

public class Home {
    public static void main(String[] args) {
        ArrayList<Alumno> alumnos = leerFichero();
        DOManager dom = new DOManager(alumnos);
        dom.createDocument();
        try {
            dom.transformMyDocumentToXML();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static ArrayList<Alumno> leerFichero() {
        Alumno alumno;
        ArrayList<Alumno> alumnos = new ArrayList<>();
        File file = new File("alumnos.txt");
        System.out.println("leyendo....");
        FileInputStream fis;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

            while (true) {
                alumno = (Alumno) ois.readObject();
                alumnos.add(alumno);
            }
        } catch (EOFException e) {
            try {
                ois.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return alumnos;
    }
}
