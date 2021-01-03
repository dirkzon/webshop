<template>
  <div>
    <TopBar></TopBar>
    <span>{{error}}</span>
    <v-col style="width: fit-content">
      <div v-bind:key="product.id" v-for="product in products">
        <v-card width="600"
                class="mx-8 my-8">
          <v-responsive :aspect-ratio="10/3">
            <v-row>
              <v-img contain
                     aspect-ratio="1/1"
                     class="grey lighten-3"
                     max-width="220"
                     min-width="200"
                     min-height="180"
                     :src='product.image.url'>
              </v-img>
              <div>
                <v-card-title>{{product.name}}</v-card-title>
                <v-card-subtitle>${{product.price.toFixed(2)}}</v-card-subtitle>
              </div>
              <v-spacer></v-spacer>
              <v-btn style="margin:20px"
                      @click="removeProduct(product.id)"
                     flat
                     icon
                     data-cy="remove">
                <v-icon x-large>delete</v-icon>
              </v-btn>
            </v-row>
          </v-responsive>
        </v-card>
      </div>
      <v-divider></v-divider>
    </v-col>

    <v-card-title data-cy="total">Total: ${{total.toFixed(2)}}</v-card-title>
  </div>
</template>

<script>
import TopBar from "@/components/TopBar";
import productService from "@/services/product-service";

export default {
  name: "ShoppingCart",
  components: {TopBar},
  data(){
    return{
      products:[],
      error:'',
      total:0,
    }
  },
  async mounted() {
    let cart = JSON.parse(this.$cookies.get("cart"));
    console.log(cart);
    if(cart == null || cart.length < 0){
      this.error = "There are no products in your cart."
    }else{
      for (let i = 0; i < cart.length; i++) {
        await this.getProduct(cart[i]);
      }
    }
    await this.getTotalPrice();
  },
  methods:{
    async getProduct(id){
      if(id > 0){
        let product = await productService.getProductById(id);
        this.products.push(product);
      }
    },
    removeProduct: function(product){
      let cart = JSON.parse(this.$cookies.get("cart"));
      for (let i = 0; i < cart.length; i++) {
        if(cart[i] == product){
          cart.splice(i, 1);
          this.products.splice(i,1);
        }
      }
      this.$cookies.set("cart", JSON.stringify(cart));
      this.getTotalPrice();
    },
    getTotalPrice: function(){
      this.total = 0;
      for (let i = 0; i < this.products.length; i++){
        this.total += this.products[i].price;
      }
    },
  },
}
</script>

<style scoped>

</style>
