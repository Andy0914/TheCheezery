package urias.andres.thecheezery

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class ProductsActivity : AppCompatActivity() {
    var coldDrinks = ArrayList<Product>()
    var hotDrinks = ArrayList<Product>()
    var sweets = ArrayList<Product>()
    var salties = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        if (coldDrinks.size == 0 && hotDrinks.size == 0 && sweets.size ==0 && salties.size == 0) {
            addProducts()
        }
        var listView: ListView = findViewById(R.id.listview) as ListView
        var imageTitle: ImageView = findViewById(R.id.imagetitle)
        var valor = this.intent.extras?.getString("type")

        var adaptador: AdapterProduct = AdapterProduct( hotDrinks, this) as AdapterProduct
        if (valor.equals("hot")) {
            adaptador = AdapterProduct( hotDrinks, this)
            imageTitle.setImageResource(R.drawable.hotdrinks)
        }else if (valor.equals("cold")){
            adaptador = AdapterProduct( coldDrinks, this)
            imageTitle.setImageResource(R.drawable.colddrinks)
        }else if (valor.equals("sweets")){
            adaptador = AdapterProduct( sweets, this)
            imageTitle.setImageResource(R.drawable.sweets)
        }else if (valor.equals("salties")) {
            adaptador = AdapterProduct(salties, this)
            imageTitle.setImageResource(R.drawable.salties)
        }

        listView.adapter = adaptador
    }
    fun addProducts(){
        coldDrinks.add(Product("Caramel Frap", R.drawable.caramelfrap, "Caramel syrup meets coffee, milk and ice and whipped cream and buttery caramel sauce layer the love on top.", 5.00))
        coldDrinks.add(Product("Chocolate Frap", R.drawable.chocolatefrap, "Rich mocha-flavored sauce meets up with chocolaty chips, milk and ice for a blender bash.", 6.00))
        coldDrinks.add(Product("Cold Brew", R.drawable.coldbrew, "Created by steeping medium-to-coarse ground coffee in room temperature water for 12 hours or longer.", 3.00))
        coldDrinks.add(Product("Matcha Latte", R.drawable.matcha, "Leafy taste of matcha green tea powder with creamy milk and a little sugar for a flavor balance that will leave you feeling ready and raring to go.", 4.00))
        coldDrinks.add(Product("Oreo Milkshake", R.drawable.oreomilkshake, "Chocolate ice cream, and oreo cookies. Topped with whipped cream with cocoa and chocolate syrup.", 7.00))
        coldDrinks.add(Product("Peanut Milkshake", R.drawable.peanutmilkshake, "Vanilla ice cream, mixed with peanut butter and chocolate.", 7.00))

        hotDrinks.add(Product("Latte", R.drawable.latte, "Coffee drink made with espresso and steamed milk", 6.00))
        hotDrinks.add(Product("Hot chocolate", R.drawable.hotchocolate, "Heated drink consisting of shaved chocolate, topped with marshmallows.", 5.00))
        hotDrinks.add(Product("Espresso", R.drawable.espresso, "Full-flavored, concentrated form of coffee.", 4.00))
        hotDrinks.add(Product("Chai Latte", R.drawable.chailatte, "Spiced tea concentrate with milk", 6.00))
        hotDrinks.add(Product("Capuccino", R.drawable.capuccino, "A cappuccino is an espresso-based coffee drink, prepared with steamed foam.", 7.00))
        hotDrinks.add(Product("American coffee", R.drawable.americano, "Espresso with hot water", 2.00))

        sweets.add(Product("Blueberry cake", R.drawable.blueberrycake, "Vanilla cake flavor, topped with cheese topping and blueberries.", 6.00))
        sweets.add(Product("Chocolate cupcake", R.drawable.chocolatecupcake, "Chocolate cupcakes topped with butter cream and cherries.", 3.00))
        sweets.add(Product("Lemon tartalette", R.drawable.lemontartalette, "Pastry shell with a lemon flavored filling.", 4.00))
        sweets.add(Product("Red Velvet cake", R.drawable.redvelvetcake, "Soft, moist, buttery cake topped with an easy cream cheese frosting.", 6.00))
        sweets.add(Product("Cherry cheesecake", R.drawable. strawberrycheesecake, "This cherry topped cheesecake is positively creamy and delicious and will be your new favorite dessert.", 7.00))
        sweets.add(Product("Tiramisu", R.drawable.tiramisu, "Coffee-flavored Italian dessert.", 6.00))

        salties.add(Product("Chicken crepes", R.drawable.chickencrepes, "Fine crepes stuffed with Alfredo chicken, spinach and mushrooms.", 6.00))
        salties.add(Product("Club Sandwich", R.drawable.clubsandwich, "A delicious sandwich served with french fries.", 5.00))
        salties.add(Product("Panini", R.drawable.hampanini, "Sandwich made with Italian bread  served warmed by grilling.", 4.00))
        salties.add(Product("Philly cheese steak", R.drawable. phillycheesesteak, "Smothered in grilled onions, green peppers, mushrooms, and Provolone.", 6.00))
        salties.add(Product("Nachos", R.drawable. nachos, "Tortilla chips layered with beef and   melted cheddar cheese. Served with fried beans, guacamole, pico de gallo, and sour topping.",  7.00))

    }

    private class AdapterProduct: BaseAdapter {
        var products = ArrayList<Product>()
        var context: Context? = null
        constructor(listaProductos:ArrayList<Product>, contexto: Context?){
            this.products = listaProductos
            this.context = contexto
        }

        override fun getCount(): Int {
            return products.size
        }

        override fun getItem(p0: Int): Any {
            return products[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var prod = products[p0]
            var inflador = LayoutInflater.from(context)
            var view = inflador.inflate(R.layout.product_view, null)

            var image = view.findViewById(R.id.product_img) as ImageView
            var nombre = view.findViewById(R.id.product_name) as TextView
            var desc = view.findViewById(R.id.product_desc) as TextView
            var precio = view.findViewById(R.id.product_price) as TextView

            image.setImageResource(prod.image)
            nombre.text = prod.name
            desc.text = prod.descriptor
            precio.text = "$${prod.price}"
            return view
        }

    }
}