<template>
  <div>
    <TopBar></TopBar>
    <div v-if="this.products.length == 0">
      <span> You have no products in your shopping cart </span>
    </div>
    <span>Total: {{this.totalPrice}}</span>
    <v-col no-gutters>
      <div v-bind:key="product.id" v-for="product in products">
        <CartProduct v-bind:product="product"></CartProduct>
      </div>
    </v-col>
  </div>
</template>

<script>
import axios from "axios";
import CartProduct from "@/components/CartProduct";
import TopBar from "@/components/TopBar";

export default {
  name: "ViewCart",
  components:{CartProduct, TopBar},
  data(){
    return{
      products: [],
      totalPrice: 0,
    }
  },
  mounted() {
    var cart = localStorage.getItem("cart");
    cart = (cart) ? JSON.parse(cart): [];
    cart.forEach(this.GetProduct)
  },
  methods:{
    GetProduct: function (item) {
      axios
          .get('http://localhost:4545/v1/products/' + item)
          .then(response => this.products.push(response.data))
          .catch(error => alert(error))
    },
    RemoveProduct: function (){

    }
  },
  watch:{
    products: function () {
      for(var product of this.products){
        this.totalPrice += product.price;
      }
    }
  }
}
</script>

<style scoped>

</style>