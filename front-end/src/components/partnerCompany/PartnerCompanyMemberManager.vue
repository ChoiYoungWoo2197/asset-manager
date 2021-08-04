<template>
  <div class="card card-primary card-outline">
    <div class="card-body ">
      <h6 class="card-subtitle mb-4 "><b>담당자</b> </h6>
      <div class="form-row callout callout-info">
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
import PartnerCompanyMember from "@/components/partnerCompany/PartnerCompanyMember.vue";

export default {
  name: "PartnerCompanyMemberManager",
  components: {
    PartnerCompanyMember
  },
  props : {
    pDatas : Array,
  },
  data() {
    return {
      name : '',
      phone : '',
      directPhone : '',
      datas : [],
      removeDatas : [],
      index : 1,
    }
  },
  watch: {
    pDatas() {
      this.datas = this.pDatas;
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
        'id' : this.getMaxId(),
        'name' : this.name,
        'phone' : this.phone,
        'directPhone' : this.directPhone,
        'useYn' : true,
      });

      this.clearData(false);
    },
    clearData(isDatasInit) {
      this.name = '';
      this.phone =  '';
      this.directPhone = '';
      this.removeDatas = [];
      if(isDatasInit === true) {
        this.datas = [];
      }

    },
    handleDeletedPartnerCompanyMemberData(data) {
      const index = this.datas.find((partnerCompanyMember, index) => {
        if(partnerCompanyMember.id === data.id) {
          return index;
        }
      });
      this.removeDatas.push(data);
      // console.log(this.removeDatas, '222222222')
      this.datas.splice(index, 1);
    },
    handleChangedPartnerCompanyMemberData(data) {
      this.datas.find((partnerCompanyMember) => {
        if(partnerCompanyMember.id === data.id) {
          partnerCompanyMember.name = data.name;
          partnerCompanyMember.phone = data.phone;
          partnerCompanyMember.directPhone = data.directPhone;
        }
      });
    },
    getDatas() {
      return this.datas;
    },
    getRemoveDatas() {
      return this.removeDatas;
    },
    getMaxId() {
      let maxId = 0;
      this.datas.find(partnerCompanyMember => {
        if(maxId < partnerCompanyMember.id) {
          maxId = partnerCompanyMember.id;
        }
      })
      return maxId+1;
    }
  }
}
</script>

<style scoped>

</style>