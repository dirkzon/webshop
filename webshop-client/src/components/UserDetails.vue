<template>
  <v-layout justify-center>
    <v-card  class="mx-8 my-8"
             width="70%">
      <v-responsive :aspect-ratio="14/7" >
        <v-row no-gutters>
          <v-responsive :aspect-ratio="14/7" >
            <v-card flat>
              <v-form>
                <v-card-subtitle>Name:</v-card-subtitle>
                <v-text-field
                    v-model="name"
                    outlined
                    :counter="20"
                    required>
                </v-text-field>
                <v-card-subtitle>e-mail:</v-card-subtitle>
                <v-text-field
                    v-model="email"
                    outlined
                    :counter="20"
                    :rules="emailRules"
                    required>
                </v-text-field>
              </v-form>
            </v-card>
          </v-responsive>
          <v-card>
            <v-img v-bind:src="imageURL"
                   :aspect-ratio="1/1"
                   height="400"
                   width="400">
            </v-img>
            <v-file-input
                @change="changeImage"
                accept="image/*"
                prepend-icon="attach_file"
            ></v-file-input>
          </v-card>
        </v-row>
      </v-responsive>
    </v-card>
  </v-layout>
</template>

<script>
export default {
  name: "UserDetails",
  props: ["user"],
  data() {
    return {
      imageURL: "",
      name: "",
      email: "",
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+/.test(v) || 'E-mail must be valid',
      ],
    }
  },
  methods: {
    changeImage: function (event) {
      var reader = new FileReader();
      reader.onload = (e) =>{
        this.imageURL = e.target.result
      }
      reader.readAsDataURL(event)
    }
  },
  watch:{
    user: function (){
      this.imageURL = this.user.avatar.url;
      this.name = this.user.name;
      this.email = this.user.EMail;
    }
  }
}
</script>

<style scoped>
.v-card{
  padding: 40px;
  margin: 20px;
}
</style>
