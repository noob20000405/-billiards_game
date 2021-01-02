public class Joueur {
    // 玩家的球竿 球带
    private Bande bande;
    private Poche poche;
    // 是否轮到此人击球
    private boolean enTour;
    // 此人是否获胜
    private boolean gagneur;
    // 玩家id
    private int id;
    private static int cpt = 0;

    public Joueur(Bande b, Poche p, boolean enTour) {
        cpt++;
        id = cpt;
        bande = b;
        poche = p;
        this.enTour = enTour;
        gagneur = false;
    }

    // 交换击球
    public void changerTour() {
        if (enTour) {
            enTour = false;
        } else {
            enTour = true;
        }
    }

    // 是否轮到此人
    public boolean estEnTour() {
        return enTour;
    }

    public int getId() {
        return id;
    }

    // 让此人胜利
    public void gagner() {
        gagneur = true;
    }

    // 此人是否获胜
    public boolean estGagneur() {
        return gagneur;
    }

    // 获取此人球带
    public Poche getPoche() {
        return poche;
    }

}
