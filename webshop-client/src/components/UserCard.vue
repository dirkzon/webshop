<template>
  <v-subheader style="margin:15px">
    <v-avatar>
      <img :src="user.avatar.url"/>
    </v-avatar>
    <v-tooltip top>
      <template v-slot:activator="{ on, attrs }">
        <v-card-title v-bind="attrs"
                      v-on="on">
          {{user.account.username}}
        </v-card-title>
      </template>
      <span>
        joined: {{user.account.joined}}
      </span>
    </v-tooltip>
  </v-subheader>

</template>

<script>
export default {
  name: "UserCard",
  props:['user']
}
</script>
