package models.vehicle

/**
 * -------------------------------------------------------------------------
 * Demo Data
 */
enum class VehicleType(
    val image: String,
    val label: String
) {
    Car(
        image = "https://unsplash.com/photos/YApiWyp0lqo/download?ixid=MnwxMjA3fDB8MXxzZWFyY2h8NHx8Y2FyfHwwfHx8fDE2MzgyOTM5NzQ&force=true&w=640",
        label = "Car"
    ),
    Jeep(
        image = "https://unsplash.com/photos/yDekvyZ52dU/download?ixid=MnwxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNjM4MzQxNTE3&force=true&w=640",
        label = "Jeep"
    ),
    DeliveryVan(
        image = "https://unsplash.com/photos/ZdySMOIicMo/download?ixid=MnwxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNjM4MzQxNTkz&force=true&w=640",
        label = "Delivery Van"
    ),
    MiniBus(
        image = "https://unsplash.com/photos/Fkwj-xk6yck/download?ixid=MnwxMjA3fDB8MXxzZWFyY2h8M3x8bWluaSUyMGJ1c3x8MHx8fHwxNjM4MzQxNjEz&force=true&w=640",
        label = "Mini Bus"
    ),
    MiniTruck(
        image = "https://unsplash.com/photos/bW2ppHnXOS4/download?ixid=MnwxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNjM4MzQxNjc2&force=true&w=640",
        label = "Mini Truck"
    ),
    Bus(
        image = "https://unsplash.com/photos/T5jzpRTVF1U/download?ixid=MnwxMjA3fDB8MXxzZWFyY2h8OHx8YnVzfHwwfHx8fDE2MzgyOTY2NzI&force=true&w=640",
        label = "Bus"
    ),
    Truck(
        image = "https://unsplash.com/photos/ki19VJAGh6w/download?ixid=MnwxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNjM4MzQxNTI3&force=true&w=640",
        label = "Truck"
    ),
    Lorry(
        image = "https://unsplash.com/photos/20qcctvyR1Q/download?ixid=MnwxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNjM4MzQxODQ0&force=true&w=640",
        label = "Lorry"
    ),
    Tractor(
        image = "https://unsplash.com/photos/zTY0RQgqb5g/download?ixid=MnwxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNjM4MzQxODc5&force=true&w=640",
        label = "Tractor"
    ),
    JCB(
        image = "https://unsplash.com/photos/xY0s9DJrANM/download?ixid=MnwxMjA3fDB8MXxzZWFyY2h8Mnx8amNifHwwfHx8fDE2MzgyNjUyNTg&force=true&w=640",
        label = "JCB"
    ),
    Tipper(
        image = "https://s3-ap-southeast-1.amazonaws.com/countrysites-tatamotors-com/countrysite/wp-content/uploads/sites/5/2016/03/14121835/16t-gallery03.jpg",
        label = "Tipper"
    ),
    Roller(
        image = "https://p.globalsources.com/IMAGES/PDT/B1175663663/road-roller.jpg",
        label = "Roller"
    ),
    Dozer(
        image = "https://dreamcivil.com/wp-content/uploads/2021/08/excavator-min.jpg",
        label = "Dozer"
    ),
    Crane(
        image = "https://marvel-b1-cdn.bc0a.com/f00000000209590/www.worldhighways.com/sites/ropl-wh/files/63580.jpg",
        label = "Crane"
    ),
    FireBrigade(
        image = "https://5.imimg.com/data5/EE/FS/MY-8313669/dsc00219t-500x500.jpg",
        label = "Fire Brigade"
    ),
    Loader(
        image = "https://marvel-b1-cdn.bc0a.com/f00000000209590/www.worldhighways.com/sites/ropl-wh/files/115759.jpg",
        label = "Loader"
    ),
    Excavator(
        image = "http://www.basiccivilengineering.com/wp-content/uploads/2015/04/excavator-1024x682.jpg",
        label = "Excavator"
    ),
    Forklift(
        image = "https://cdn.godrej.com/img/InnerPR/2I3A03601_05023556.jpg",
        label = "Forklift"
    ),
    PowerTiller(
        image = "https://www.shrachiagrimech.com/images/products/virat111.jpg",
        label = "Power Tiller"
    ),
    AutoRickshaw(
        image = "https://upload.wikimedia.org/wikipedia/commons/4/49/Bajaj_auto-rickshaw_in_Sri_Lanka.jpg",
        label = "Auto Rickshaw"
    ),
    Unknown(
        image = "https://www.marketing91.com/wp-content/uploads/2018/08/Product-Portfolio-1.jpg",
        label = "No Vehicle"
    ),
    Other(
        image = "https://upload.wikimedia.org/wikipedia/commons/4/49/Bajaj_auto-rickshaw_in_Sri_Lanka.jpg",
        label = "Other"
    );
}