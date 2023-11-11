import Data.Entities.Catalog;
import Data.Entities.Products.Product;
import Data.Entities.Users.Client;
import Data.Entities.Users.Seller;
import Utils.PrintUtil;

import java.util.*;

public class Driver {
    public static void main(String[] args) {


        PrintUtil.welcomeMessage("Bienvenue dans la Plateforme Unishop !!!");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez une option: ");
        System.out.println("1. S'inscrire");
        System.out.println("2. Se connecter") ;
        int option = -1 ;
        try {
            option = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.err.println("Ooops! option doit etre chiffre 1 ou 2");
            System.exit(-1);
        }
        if (option == 1) {
            System.out.println("Choisissez une option d'inscription");
            System.out.println("1. Vendeur");
            System.out.println("2. Acheteur");
            try {
                option = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.err.println("Ooops! option d'inscription doit etre chiffre 1 ou 2");
                System.exit(-1);
            }
            if (option == 2) {
                scanner = new Scanner(System.in);
                System.out.println("Saisissez vos informations") ;

                System.out.print("Prenom: ");
                String firstName = null;
                try {
                    firstName = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! prenom doit etre une chaine de caracteres");
                    System.exit(-1);
                }

                ///String firstName = App.getClientStrInput(scanner,"Prenom") ;

                System.out.print("Nom: ");
                String lastName = null ;
                try {
                    lastName = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! nom doit etre une chaine de caracteres");
                    System.exit(-1);
                }


                System.out.print("Pseudo: ");
                String pseudo = null;
                try {
                    pseudo = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! pseudo doit etre une chaine de caracteres");
                    System.exit(-1);
                }

                System.out.print("Email: ");
                String email = null;
                try {
                    email = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! email doit etre une chaine de caracteres");
                    System.exit(-1);
                }

                scanner = new Scanner(System.in);
                System.out.print("Numero: ");
                long number = 0;
                try {
                    number = scanner.nextLong();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! numero doit etre un nombre");
                    System.exit(-1);
                }

                //long number = App.getClientNumInput(scanner,"Numero") ;

                scanner = new Scanner(System.in);
                System.out.print("Adresse de livraison: ");
                String shipAddress = null;
                try {
                    shipAddress = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! adresse de livraison doit etre une chaine de caractere");
                    System.exit(-1);
                }

                scanner = new Scanner(System.in) ;
                System.out.println("Confirmer vos informations: ");
                System.out.println("1. oui");
                System.out.println("2. non");
                try {
                    option = scanner.nextInt() ;
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! option doit etre chiffre 1 ou 2");
                    System.exit(-1);
                }
                if (option == 1) {
                    System.out.println("votre compte a ete cree avec succes");
                }
                else {
                    System.out.println("echec de la creation de votre compte");
                }

                Client client=new Client(firstName,lastName,email,pseudo,number,shipAddress);
                for (Object[] obj: Catalog.catalogMap.values()){
                    System.out.println(obj[0]);
                }
            }
            else {
                scanner = new Scanner(System.in) ;
                System.out.println("Vous devez offrir au moins un produit à vendre au prealable") ;
                System.out.println("1. Offrir un produit à vendre:") ;
                try {
                    option = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! option doit etre chiffre 1");
                    System.exit(-1);
                }

                System.out.println("Choisissez une categorie de produit a vendre:") ;
                System.out.println("1. Livres et Manuels");
                System.out.println("2. Ressource d'apprentissage");
                System.out.println("3. Article de papeterie") ;
                System.out.println("4. Materiel informatique");
                System.out.println("5. Equipement de bureau") ;
                try {
                    option = scanner.nextInt() ;
                } catch (InputMismatchException e) {
                    System.err.println("Oops ! option non valide");
                    System.exit(-1);
                }

                if (option == 1) {
                    System.out.println("Donnez les informations du Livre/Manuel: ");
                    scanner = new Scanner(System.in) ;

                    System.out.print("titre: ") ;
                    String title = null ;
                    try {
                        title = scanner.nextLine() ;
                    } catch (InputMismatchException e) {
                        System.err.println("titre doit etre une chaine de caracteres");
                        System.exit(-1);
                    }

                    System.out.print("description: ") ;
                    String desc = null ;
                    try {
                        title = scanner.nextLine() ;
                    } catch (InputMismatchException e) {
                        System.err.println("description doit etre une chaine de caracteres");
                        System.exit(-1);
                    }


                    System.out.print("author: ") ;
                    String author = null ;
                    try {
                        author = scanner.nextLine() ;
                    } catch (InputMismatchException e) {
                        System.err.println("auteur doit etre une chaine de caracteres");
                        System.exit(-1);
                    }

                    System.out.print("ISBN: ") ;
                    String isbn = null ;
                    try {
                        isbn = scanner.nextLine() ;
                    } catch (InputMismatchException e) {
                        System.err.println("isbn doit etre une chaine de caracteres");
                        System.exit(-1);
                    }

                    System.out.print("editor: ") ;
                    String editor = null ;
                    try {
                        editor = scanner.nextLine() ;
                    } catch (InputMismatchException e) {
                        System.err.println("editeur doit etre une chaine de caracteres");
                        System.exit(-1);
                    }

                    System.out.println("possede numero d'edition: ");
                    System.out.println("1. oui");
                    System.out.println("2. non");
                    try {
                        option = scanner.nextInt() ;
                    } catch (InputMismatchException e) {
                        System.err.println("Ooops! option doit etre chiffre 1 ou 2");
                        System.exit(-1);
                    }

                    if (option == 1) {
                        scanner = new Scanner(System.in) ;
                        System.out.print("numero d'edition: ");
                        String editionNum = null ;
                        try {
                            editionNum = scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.err.println("numero d'edition doit etre une chaine de caracteres");
                            System.exit(-1);
                        }
                    }

                    System.out.print("genre: ") ;
                    String genre = null ;
                    try {
                        genre = scanner.nextLine() ;
                    } catch (InputMismatchException e) {
                        System.err.println("genre doit etre une chaine de caracteres");
                        System.exit(-1);
                    }

                    System.out.print("date de parution (DD/MM/YYYY): ") ;
                    String pubDate = null ;
                    try {
                        pubDate = scanner.nextLine() ;
                    } catch (InputMismatchException e) {
                        System.err.println("date de parution doit sous format (DD/MM/YYYY) en chaine de caracteres");
                        System.exit(-1);
                    }

                    System.out.println("possede numero de volume: ");
                    System.out.println("1. oui");
                    System.out.println("2. non");
                    try {
                        option = scanner.nextInt() ;
                    } catch (InputMismatchException e) {
                        System.err.println("Ooops! option doit etre chiffre 1 ou 2");
                        System.exit(-1);
                    }

                    if (option == 1) {
                        System.out.print("numero de volume: ");
                        int volNum ;
                        try {
                            volNum = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.err.println("numero de volume doit etre une chaine de caracteres");
                            System.exit(-1);
                        }
                    }

                    System.out.print("Quantite: ");
                    int intQuantity = 0 ;
                    try {
                        intQuantity = scanner.nextInt() ;
                    } catch (InputMismatchException e) {
                        System.err.println("quantite doit etre un nombre");
                        System.exit(-1);
                    }

                    System.out.println("offrir des points bonus pour le produit:") ;
                    System.out.println("1. oui");
                    System.out.println("2. non");
                    try {
                        option = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.err.println("Ooops! option doit etre chiffre 1 ou 2");
                        System.exit(-1);
                    }
                    int points = 1 ;
                    if (option ==  1) {
                        System.out.print("bonus/$: ");
                        try {
                            points = scanner.nextInt() ;
                        } catch (InputMismatchException e) {
                            System.err.println("points bonus doit etre un nombre");
                            System.exit(-1);
                        }
                        if (points >= 20) {
                            System.out.println("point bonus par $ ne peut pas depasser 20");
                            System.exit(-1);
                        }
                    }

                    System.out.print("Price: ");
                    double price = 0 ;
                    try {
                        price = scanner.nextDouble() ;
                    } catch (InputMismatchException e) {
                        System.err.println("prix doit etre un nombre");
                        System.exit(-1);
                    }

                }

                scanner = new Scanner(System.in);
                System.out.println();
                System.out.println("Saisissez vos informations") ;
                System.out.print("Prenom: ");
                String firstName = null;
                try {
                    firstName = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! prenom doit etre une chaine de caracteres");
                    System.exit(-1);
                }

                System.out.print("Nom: ");
                String lastName = null ;
                try {
                    lastName = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! nom doit etre une chaine de caracteres");
                    System.exit(-1);
                }


                System.out.print("Pseudo: ");
                String pseudo = null;
                try {
                    pseudo = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! pseudo doit etre une chaine de caracteres");
                    System.exit(-1);
                }

                System.out.print("Email: ");
                String email = null;
                try {
                    email = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! email doit etre une chaine de caracteres");
                    System.exit(-1);
                }

                scanner = new Scanner(System.in);
                System.out.print("Numero: ");
                long number = 0;
                try {
                    number = scanner.nextLong();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! numero doit etre un nombre");
                    System.exit(-1);
                }

                scanner = new Scanner(System.in) ;
                System.out.println("Confirmer vos informations: ");
                System.out.println("1. oui");
                System.out.println("2. non");
                try {
                    option = scanner.nextInt() ;
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! option doit etre chiffre 1 ou 2");
                    System.exit(-1);
                }
                if (option == 1) {
                    System.out.println("votre compte a ete cree avec succes");
                }
                else {
                    System.out.println("echec de la creation de votre compte");
                }

            }
        }


    }
}