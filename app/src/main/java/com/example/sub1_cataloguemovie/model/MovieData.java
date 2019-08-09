package com.example.sub1_cataloguemovie.model;

import java.util.ArrayList;

/**
 * Created by auzan on 6/29/2019.
 * Github: @auzanassdq
 */
public class MovieData {

    private static String[][] dataMovie = new String[][]{
            {"Toy Story 4",
                    "Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                    "https://image.tmdb.org/t/p/w1280/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg",
                    "2019"},

            {"Spider-Man: Far from Home",
                    "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                    "https://image.tmdb.org/t/p/w1280/2ieNRjf4jMNyOOnGyTWrLY6HLTA.jpg",
                    "2019"},

            {"Shazam!",
                    "A boy is given the ability to become an adult superhero in times of need with a single magic word.",
                    "https://image.tmdb.org/t/p/w1280/xnopI5Xtky18MPhK40cZAGAOVeV.jpg",
                    "2019"},

            {"Detective Conan: The Fist of Blue Sapphire",
                    "23rd movie in the \"Detective Conan\" franchise.",
                    "https://image.tmdb.org/t/p/w1280/1GyvpwvgswOrHvxjnw2FBLNkTyo.jpg",
                    "2019"},

            {"Annabelle Comes Home",
                    "Determined to keep Annabelle from wreaking more havoc, demonologists Ed and Lorraine Warren bring the possessed doll to the locked artifacts room in their home, placing her “safely” behind sacred glass and enlisting a priest’s holy blessing. But an unholy night of horror awaits as Annabelle awakens the evil spirits in the room, who all set their sights on a new target—the Warrens' ten-year-old daughter, Judy, and her friends.",
                    "https://image.tmdb.org/t/p/w1280/jsbt03UnPPt14AYRMcGaoqtwNxQ.jpg",
                    "2019"},

            {"Dark Phoenix",
                    "The X-Men face their most formidable and powerful foe when one of their own, Jean Grey, starts to spiral out of control. During a rescue mission in outer space, Jean is nearly killed when she's hit by a mysterious cosmic force. Once she returns home, this force not only makes her infinitely more powerful, but far more unstable. The X-Men must now band together to save her soul and battle aliens that want to use Grey's new abilities to rule the galaxy.",
                    "https://image.tmdb.org/t/p/w1280/kZv92eTc0Gg3mKxqjjDAM73z9cy.jpg",
                    "2019"},

            {"John Wick: Chapter 3 – Parabellum",
                    "Super-assassin John Wick returns with a $14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin’s guild, the High Table, John Wick is excommunicado, but the world’s most ruthless hit men and women await his every turn.",
                    "https://image.tmdb.org/t/p/w1280/ziEuG1essDuWuC5lpWUaw1uXY2O.jpg",
                    "2019"},

            {"Pet Sematary",
                    "Dr. Louis Creed and his wife, Rachel, relocate from Boston to rural Maine with their two young children. The couple soon discover a mysterious burial ground hidden deep in the woods near their new home. When tragedy strikes, Louis turns to his neighbour Jud Crandall, setting off a perilous chain reaction that unleashes an unspeakable evil with horrific consequences.",
                    "https://image.tmdb.org/t/p/w1280/7SPhr7Qj39vbnfF9O2qHRYaKHAL.jpg",
                    "2019"},

            {"Captain Marvel",
                    "The story follows Carol Danvers as she becomes one of the universe’s most powerful heroes when Earth is caught in the middle of a galactic war between two alien races. Set in the 1990s, Captain Marvel is an all-new adventure from a previously unseen period in the history of the Marvel Cinematic Universe.",
                    "https://image.tmdb.org/t/p/w1280/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg",
                    "2019"},

            {"Avengers: Endgame",
                    "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
                    "https://image.tmdb.org/t/p/w1280/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
                    "2019"},
    };

    private static String[][] dataTvShow = new String[][]{
            {"One-Punch Man ",
                    "Saitama is a hero who only became a hero for fun. After three years of “special” training, though, he’s become so strong that he’s practically invincible. In fact, he’s too strong—even his mightiest opponents are taken out with a single punch, and it turns out that being devastatingly powerful is actually kind of a bore. With his passion for being a hero lost along with his hair, yet still faced with new enemies every day, how much longer can he keep it going?",
                    "https://image.tmdb.org/t/p/w1280/iE3s0lG5QVdEHOEZnoAxjmMtvne.jpg",
                    "2015"},

            {"Fear the Walking Dead",
                    "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                    "https://image.tmdb.org/t/p/w1280/oYXxZIiI7lVh6IUCCikImKwULHB.jpg",
                    "2015"},

            {"Marvel's Agents of S.H.I.E.L.D.",
                    "Agent Phil Coulson of S.H.I.E.L.D. (Strategic Homeland Intervention, Enforcement and Logistics Division) puts together a team of agents to investigate the new, the strange and the unknown around the globe, protecting the ordinary from the extraordinary.",
                    "https://image.tmdb.org/t/p/w1280/cXiETfFK1BTLest5fhTLfDLRdL6.jpg",
                    "2013"},

            {"The 100",
                    "100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.",
                    "https://image.tmdb.org/t/p/w1280/wBzNjurA8ijJPF21Ggs9nbviIzi.jpg",
                    "2014"},

            {"One Piece",
                    "Years ago, the fearsome pirate king Gol D. Roger was executed, leaving a huge pile of treasure and the famous \"One Piece\" behind. Whoever claims the \"One Piece\" will be named the new pirate king. Monkey D. Luffy, a boy who consumed one of the \"Devil's Fruits\", has it in his head that he'll follow in the footsteps of his idol, the pirate Shanks, and find the One Piece. It helps, of course, that his body has the properties of rubber and he's surrounded by a bevy of skilled fighters and thieves to help him along the way. Monkey D. Luffy brings a bunch of his crew followed by, Roronoa Zoro, Nami, Usopp, Sanji, Tony-Tony Chopper, Nico Robin, Franky, and Brook. They will do anything to get the One Piece and become King of the Pirates!",
                    "https://image.tmdb.org/t/p/w1280/gJI77i79KnRuc9mGPKADPZWAE8O.jpg",
                    "1999"},

            {"Legion",
                    "David Haller, AKA Legion, is a troubled young man who may be more than human. Diagnosed as schizophrenic, David has been in and out of psychiatric hospitals for years. But after a strange encounter with a fellow patient, he’s confronted with the possibility that the voices he hears and the visions he sees might be real.",
                    "https://image.tmdb.org/t/p/w1280/vT0Zsbm4GWd7llNjgWEtwY0CqOv.jpg",
                    "2017"},

            {"Stranger Things",
                    "When a young boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying supernatural forces, and one strange little girl.",
                    "https://image.tmdb.org/t/p/w1280/x2LSRK2Cm7MZhjluni1msVJ3wDF.jpg",
                    "2016"},

            {"Wise Man’s Grandchild",
                    "A young man who had surely died in an accident, was reborn in another world as a baby! After that, he was picked up by the patriot hero \"Sage\" Merlin Wolford and was given the name Shin. He was raised as a grandson by Merlin and soaked up Merlin's teachings, earning him some shocking powers; however, when he became 15, his grandfather Merlin said, \"I forgot to teach him common sense!\"",
                    "https://image.tmdb.org/t/p/w1280/vdRt0W5sZDLlshT2o3y7c7gZYJA.jpg",
                    "2019"},

            {"Demon Slayer: Kimetsu no Yaiba",
                    "It is the Taisho Period in Japan. Tanjiro, a kindhearted boy who sells charcoal for a living, finds his family slaughtered by a demon. To make matters worse, his younger sister Nezuko, the sole survivor, has been transformed into a demon herself.\n" +
                            "\n" +
                            "Though devastated by this grim reality, Tanjiro resolves to become a “demon slayer” so that he can turn his sister back into a human, and kill the demon that massacred his family.",
                    "https://image.tmdb.org/t/p/w1280/taT33NroOl2Fn8bUGj8bwdmNw3G.jpg",
                    "2019"},

            {"Swamp Thing",
                    "CDC researcher Abby Arcane investigates what seems to be a deadly swamp-born virus in a small town in Louisiana but she soon discovers that the swamp holds mystical and terrifying secrets. When unexplainable and chilling horrors emerge from the murky marsh, no one is safe.",
                    "https://image.tmdb.org/t/p/w1280/dD3HcMczLC9wNvfNzx4pZVyl6q8.jpg",
                    "2019"},
    };

    public static ArrayList<Movie> getListDataMovie(){
        ArrayList<Movie> list = new ArrayList<>();
        for (String[] aData : dataMovie) {
            Movie movie = new Movie();
            movie.setName(aData[0]);
            movie.setDesc(aData[1]);
            movie.setPhoto(aData[2]);
            movie.setYear(aData[3]);

            list.add(movie);
        }

        return list;
    }

    public static ArrayList<Movie> getListDataTvShow(){
        ArrayList<Movie> list = new ArrayList<>();
        for (String[] aData : dataTvShow) {
            Movie movie = new Movie();
            movie.setName(aData[0]);
            movie.setDesc(aData[1]);
            movie.setPhoto(aData[2]);
            movie.setYear(aData[3]);

            list.add(movie);
        }

        return list;
    }
}
