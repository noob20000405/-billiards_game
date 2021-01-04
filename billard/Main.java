public class Main {
    public static void main(String[] argv) {
        int choix;

        try {
            Global.verifierParams(argv.length);
            choix = Integer.parseInt(argv[0]);
            if (choix == 1) {
                GameStart game = new GameStart();
            } else if (choix == 2) {
                System.out.println("Regle : ");
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