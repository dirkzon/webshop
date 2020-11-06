<template>
  <div>
    <TopBar></TopBar>
    <UserDetails v-bind:user="user"></UserDetails>
  </div>
</template>

<script>
import axios from "axios";
import TopBar from "@/components/TopBar";
import UserDetails from "@/components/UserDetails";
export default {
  name: "ViewAccount",
  components:{TopBar, UserDetails},
  data(){
    return{
      user: Object
    }
  },

  mounted() {
    let url = String;
    let userId = localStorage.id;
    if(userId.includes("customer")){
      url = "http://localhost:4545/v1/customers/" + userId;
    }else{
      url = "http://localhost:4545/v1/retailers/" + userId;
    }
    axios
        .get(url)
        .then(response => (this.user = response.data))
        .catch(error => alert(error))
  }
}
</script>
