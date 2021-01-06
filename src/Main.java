public class Main {
    public static void main(String[] argv) {
        int choix;

        try {
            Global.verifierParams(argv.length);
            choix = Integer.parseInt(argv[0]);
            if (choix == 1) {
                GameStart game = new GameStart();
            } else if (choix == 2) {
                System.out.println("Bienvenu au monde du billard.");
                System.out.println("Ceci est un jeu pour deux personnes : ");
                System.out.println("Joueur 1 utilise la queue blanche et a pour objective les billes pleines");
                System.out.println("Joueur 2 utilise la queue noire et a pour objective les billes rayees");
                System.out.println("");
                System.out.println("But du jeu : ");
                System.out.println("Rentrer tous les billes de ton champ dans les trous à l'aide de la bille blanche et de la queue");
                System.out.println("Une fois votre poche est pleine, vous tentez de rentrer la bille noir dans le trou");
                System.out.println("");
                System.out.println("Instructions : Pour frapper appuyez sur le bouton gauche de la souris dans l'interface du jeu et controlez la direction de la queue pour marquer.");    
                System.out.println("Le temps d'appuie sur le bouton de la souris determine l'intensite de la force appliquée sur la bille frappée");
                System.out.println("Attention : si vous rentrez une bille de votre adversaire, vous pouvez continuer à joueur, mais c'est lui qui gagne un point.");
            } else {
                System.out.println("Vous n'avez que deux choix du parametre : 1 ou 2");
            }

        } catch (ErreurParamsException e) {
            System.out.println(e +  "\njava Main 1 : Game start\njava Main 2 : Regles du jeu");

        } catch (NumberFormatException e) {
            System.out.println("Vous n'avez que deux choix du parametre : 1 ou 2");
        }

    }
}
