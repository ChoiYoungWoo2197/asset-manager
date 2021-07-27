<template>
  <div class="form-row mt-1">
    <div class="col-md-1 text-center">
      <input type="button" class="btn btn-sm font-weight-bold" value="-" @click.prevent="clickMinusBtn">
    </div>
    <div class="col-md-3">
      <input type="text" class="form-control inputLow" placeholder="이름" v-model="name" @keyup="keyupPartnerCompanyMember">
    </div>
    <div class="col-md-4">
      <input type="text" class="form-control inputLow" placeholder="전화번호" v-model="phone" @keyup="keyupPartnerCompanyMember">
    </div>
    <div class="col-md-4">
      <input type="text" class="form-control inputHigh" placeholder="직통전화번호" v-model="directPhone" @keyup="keyupPartnerCompanyMember">
    </div>
  </div>

</template>

<script>
export default {
  name: "PartnerCompanyMember",
  props : {
    pId : String,
    pName : String,
    pPhone : String,
    pDirectPhone : String,
  },
  data() {
    return {
      id : '',
      name : '',
      phone : '',
      directPhone : '',
    }
  },
  mounted() {
    this.id = this.pId;
    this.name = this.pName;
    this.phone = this.pPhone;
    this.directPhone = this.pDirectPhone;
  },
  watch: {
  },
  methods: {
    clickMinusBtn() {
      this.$emit('deletedPartnerCompanyMemberData', {
        'id' : this.id,
        'name' : this.name,
        'phone' : this.phone,
        'directPhone' : this.directPhone,
      });
    },
    keyupPartnerCompanyMember() {
      this.$emit('changedPartnerCompanyMemberData', {
        'id' : this.id,
        'name' : this.name,
        'phone' : this.phone,
        'directPhone' : this.directPhone,
      });
    }
  }
}
</script>

<style scoped>

</style>