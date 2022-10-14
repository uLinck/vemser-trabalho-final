package model.com.shinigami.model;

public class Casa extends Imovel {
    private boolean areaDeLazer;
    private boolean garagem;

    public Casa(Endereco endereco, int qntdQuartos, int qntdBanheiros, double valorMensal, double condominio, TipoImovel tipoImovel, boolean areaDeLazer, boolean garagem) {
        super(endereco, qntdQuartos, qntdBanheiros, valorMensal, condominio, tipoImovel);
        this.areaDeLazer = areaDeLazer;
        this.garagem = garagem;
    }

    public Casa() {}



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
