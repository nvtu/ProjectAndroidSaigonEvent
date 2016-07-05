package com.example.tuvanninh.hcmcevent;

import java.util.ArrayList;

/**
 * Created by Tu Van Ninh on 04/07/2016.
 */
public class Content {
    private ArrayList<Info> locationList = new ArrayList<>();
    public Content() {

    }

    public ArrayList<Info> getLocationList(){

        String[] nameLocation = new String[]{
                "Bến Thành Market",
                "Saigon Notre-Dame Basilica",
                "Jade Emperor Pagoda",
                "Independence Palace",
                "Municipal Theatre, Ho Chi Minh City"
        };

        String[] descriptionLocation = new String[] {"Bến Thành Market (Vietnamese: Chợ Bến Thành) is a large marketplace in central " +
                "Hồ Chí Minh City, Vietnam in District 1. The market is one of the earliest surviving structures in " +
                "Saigon and an important symbol of Hồ Chí Minh City, popular with tourists seeking local handicrafts, " +
                "textiles, áo dài and souvenirs, as well as local cuisine.",
                "Notre-Dame Cathedral Basilica of Saigon officially Cathedral Basilica of Our Lady of The " +
                        "Immaculate Conception is a cathedral located in the downtown of Ho Chi Minh City, Vietnam",
                "The Jade Emperor Pagoda is a Taoist pagoda located at 73 Mai Thi Luu Street, District 1, Ho Chi Minh City, Vietnam. It was built by the Chinese community in 1909.",
                "Independence Palace, also known as Reunification Palace, built on the site of the former " +
                        "Norodom Palace, is a landmark in Ho Chi Minh City, Vietnam.",
                "The Municipal Theatre of Ho Chi Minh City, also known as Saigon Opera House, " +
                        "is an opera house in Ho Chi Minh City, Vietnam. It is an example of French Colonial architecture in Vietnam."
        };

        String[] addressLocation = new String[]{
                "Phan Bội Châu, Bến Thành, Hồ Chí Minh",
                "Bến Nghé, Ho Chi Minh City",
                "40 Điện Biên Phủ, Đa Kao, Quận 1, Hồ Chí Minh",
                "135 Nam Kỳ Khởi Nghĩa, Bến Thành, Quận 1, Hồ Chí Minh",
                "7 Lam Son Square, Ben Nghe Ward, District 1, Ho Chi Minh City"
        };

        String[] urlLocation = new String[]{
                "https://en.wikipedia.org/wiki/B%E1%BA%BFn_Th%C3%A0nh_Market",
                "https://en.wikipedia.org/wiki/Notre-Dame_Cathedral_Basilica_of_Saigon",
                "https://en.wikipedia.org/wiki/Jade_Emperor_Pagoda",
                "https://en.wikipedia.org/wiki/Independence_Palace",
                "https://en.wikipedia.org/wiki/Municipal_Theatre,_Ho_Chi_Minh_City"
        };

        String[] phoneNoLocation = new String[]{
                "0973756776",
                "0838220477",
                "",
                "",
                "0838299976"
        };

        int[] imageIdLocation = new int[] {
                R.drawable.benthanh_market,
                R.drawable.ducba,
                R.drawable.ngochoang_temple,
                R.drawable.dinhthongnhat,
                R.drawable.nhahatlon
        };

        for (int i=0; i<nameLocation.length; i++){
            locationList.add(new Info(nameLocation[i], descriptionLocation[i], phoneNoLocation[i], addressLocation[i], urlLocation[i], imageIdLocation[i]));
        }
        return locationList;
    }

}
