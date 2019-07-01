package com.example.sub1_cataloguemovie.model;

import java.util.ArrayList;

/**
 * Created by auzan on 6/29/2019.
 * Github: @auzanassdq
 */
public class MovieData {

    public static String[][] data = new String[][]{
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

    public static ArrayList<Movie> getListData(){
        ArrayList<Movie> list = new ArrayList<>();
        for (String[] aData : data) {
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
