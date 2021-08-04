<template>
  <div class="card-body specifications">
    <form>
      <template v-if="datas.length === 0">
        <div>
          <i class="fas fa-info-circle pr-1"></i>정보가 없습니다.
        </div>
      </template>
      <template v-else>
        <div class="form-group callout callout-info specs-values" v-for="data in datas" :key="data">
          <label :for="data.id">{{data.name}}<span class="text-danger"></span></label>
          <input type="text" class="form-control specs-value" :data-specs-key="data.id" :id="data.id" placeholder="" :value="data.content">
        </div>
      </template>
    </form>
  </div>
</template>

<script>
import $ from "jquery";
import axios from "axios";

export default {
  name: "AssetSpecification",
  props: {
  },
  data() {
    return {
      assetCode : null,
      category : null,
      datas : [],
    }
  },
  mounted() {

  },
  methods: {
    clearData() {
      this.assetCode = null;
      this.category = null;
      this.datas = [];
      $('.specifications .specs-values').remove();
    },
    findChild(data) {
      this.category = data;
      this.findChildByCategoryId();
    },
    findChildByCategoryId() {
      const vm = this;
      if(this.category === null) return false;

      axios.get('http://localhost:8080/api/categorys/' + vm.category.id + '/get-child'
      ).then(response => {
        vm.datas = response.data.filter(categorySpecification => categorySpecification.useYn === true);
      }).catch(e => {
        alert(e);
      });
    },
    findChildByAssetCode(assetCode) {
      const vm = this;
      axios.get('http://localhost:8080/api/assets/' + assetCode + '/get-specs-child'
      ).then(response => {
        // console.log(response, 'ddddddddddddd')
        vm.datas = [];
        response.data.forEach(data => {
          vm.datas.push({
            id : data.categorySpecificationId,
            name : data.categorySpecificationName,
            content : data.content,
          })
        })
        // vm.datas = response.data.filter(categorySpecification => categorySpecification.useYn === true);
      }).catch(e => {
        alert(e);
      });
    },
    getSpecificationDatas() {
      let specifications = [];
      $('.specifications .specs-value').each((index, input) => {
        specifications.push({
          categorySpecificationId :$(input).data("specs-key"),
          content : $(input).val()
        })
      })
      return specifications;
    }
  },


}
</script>

<style scoped>

</style>