package urnaeletronica;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class Candidato {

    public boolean jaExiste = false;
    public boolean deuCerto = false;
    public String dir = null;
    public String[][] candidatos = new String[10][6];

    public Candidato() {
        candidatos[0][0] = "00";
        candidatos[0][1] = "BRANCO";
        candidatos[0][2] = "BRANCO";
        candidatos[0][3] = "BRANCO";
        candidatos[0][4] = "0";
        candidatos[0][5] = "";
    }

    public void adicionaVoto(int posicao) {
        int votos = Integer.parseInt(candidatos[posicao][4]);
        ++votos;
        candidatos[posicao][4] = Integer.toString(votos);
    }

    public String retornarVotos(int posicao) {
        return candidatos[posicao][4];
    }

    public int verifica(String numero) {
        int posicao = -1;

        for (int i = 0; i < candidatos.length; i++) {
            if (numero.equals(candidatos[i][0])) {
                posicao = i;
                jaExiste = true;
            }
        }

        return posicao;
    }

    public BufferedImage carregaFoto() {
        JFileChooser escolheArquivo = new JFileChooser();
        BufferedImage imagem = null;

        if (escolheArquivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                File img = escolheArquivo.getSelectedFile();
                dir = img.toString();
                try {
                    imagem = ImageIO.read(img);
                    deuCerto = true;
                } catch (IOException exc) {
                    System.out.println(exc);
                }
            } catch (ClassCastException exc) {
                System.out.println(exc);
            }
        }
        return imagem;
    }

    public BufferedImage pesquisaFoto(int posicao) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(candidatos[posicao][5]));
        } catch (IOException e) {
            System.out.println(e);
        }
        return img;
    }

    public ImageIcon retornarImagemIcone(JLabel lblImg, BufferedImage buffer) {
        ImageIcon imgIcone = new ImageIcon(new ImageIcon(buffer).getImage().getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), buffer.SCALE_DEFAULT));
        return imgIcone;
    }

    public void mostrar(JLabel lblSaida, BufferedImage buffer) {
        lblSaida.setIcon(retornarImagemIcone(lblSaida, buffer));
    }

    
}
