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
    pId : Number,
    pName : String,
    pPhone : String,
    pDirectPhone : String,
    pUseYn : Boolean
  },
  data() {
    return {
      id : '',
      name : '',
      phone : '',
      directPhone : '',
      useYn : true,
    }
  },
  mounted() {
    this.id = this.pId;
    this.name = this.pName;
    this.phone = this.pPhone;
    this.directPhone = this.pDirectPhone;
    this.useYn = this.pUseYn;
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
        'useYn' : this.useYn,
      });
    },
    keyupPartnerCompanyMember() {
      this.$emit('changedPartnerCompanyMemberData', {
        'id' : this.id,
        'name' : this.name,
        'phone' : this.phone,
        'directPhone' : this.directPhone,
        'useYn' : this.useYn,
      });
    }
  }
}
</script>

<style scoped>

</style>