package model;

public class Casa extends Imovel {
    private boolean areaDeLazer;
    private boolean garagem;

    public boolean isAreaDeLazer() {
        return areaDeLazer;
    }

    public void setAreaDeLazer(boolean areaDeLazer) {
        this.areaDeLazer = areaDeLazer;
    }

    public boolean isGaragem() {
        return garagem;
    }

    public void setGaragem(boolean garagem) {
        this.garagem = garagem;
    }
}
