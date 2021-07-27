<template>
  <div class="card card-primary card-outline">
    <div class="card-body">
      <h6 class="card-subtitle mb-4 "><b>담당자</b> </h6>
      <div class="form-row">
        <div class="col-md-1 text-center">
          <input type="button" class="btn btn-sm font-weight-bold" value="+" @click.prevent="clickPlusBtn">
        </div>
        <div class="col-md-3">
          <input type="text" class="form-control inputLow" placeholder="이름" v-model="name">
        </div>
        <div class="col-md-4">
          <input type="text" class="form-control inputLow" placeholder="전화번호" v-model="phone">
        </div>
        <div class="col-md-4">
          <input type="text" class="form-control inputHigh" placeholder="직통전화번호" v-model="directPhone">
        </div>

      </div>
      <template v-for="(data) in datas" :key="data">
        <PartnerCompanyMember
            :p-id="data.id"
            :p-name="data.name"
            :p-phone="data.phone"
            :p-direct-phone="data.directPhone"
            @deletedPartnerCompanyMemberData="handleDeletedPartnerCompanyMemberData"
            @changedPartnerCompanyMemberData="handleChangedPartnerCompanyMemberData">
        </PartnerCompanyMember>
      </template>
    </div>
  </div>
</template>

<script>
// import _ from "lodash"
import PartnerCompanyMember from "@/components/PartnerCompany/PartnerCompanyMember.vue";

export default {
  name: "PartnerCompanyMemberManager",
  components: {
    PartnerCompanyMember
  },
  data() {
    return {
      name : '',
      phone : '',
      directPhone : '',
      datas : [],
      index : 1,
    }
  },
  mounted() {
  },
  methods: {
    clickPlusBtn() {
      if(this.name === "") {
        alert("담당자 이름을 입력해주세요.");
        return false;
      }

      this.datas.push({
        'id' : (this.index++).toString(),
        'name' : this.name,
        'phone' : this.phone,
        'directPhone' : this.directPhone,
      });

      this.clearData();
    },
    clearData() {
      this.name = '';
      this.phone =  '';
      this.directPhone = '';
    },
    handleDeletedPartnerCompanyMemberData(data) {
      const index = this.datas.find((partnerCompanyMember, index) => {
        if(Number(partnerCompanyMember.id) === Number(data.id)) {
          return index;
        }
      });
      this.datas.splice(index, 1);
    },
    handleChangedPartnerCompanyMemberData(data) {
      this.datas.find((partnerCompanyMember) => {
        if(partnerCompanyMember.id.indexOf(data.id) !== -1) {
          partnerCompanyMember.name = data.name;
          partnerCompanyMember.phone = data.phone;
          partnerCompanyMember.directPhone = data.directPhone;
        }
      });
    },
    getDatas() {
      return this.datas;
    }

  }
}
</script>

<style scoped>

</style>