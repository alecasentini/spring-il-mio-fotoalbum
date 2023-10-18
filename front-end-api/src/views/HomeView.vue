<script setup>
import { onMounted, ref, computed } from 'vue';
import axios from "axios";
const API_URL = "http://localhost:8080/api/v1.0/photos"
const photos = ref(null);
const searchQuery = ref('');
function getAllPhotos() {
  axios.get(API_URL)
    .then(res => {
      const data = res.data;
      photos.value = data.filter(photo => photo.visibile);
    })
    .catch(err => console.log(err));
}
onMounted(() => {
  getAllPhotos();
});

const filteredPhotos = computed(() => {
  if (!searchQuery.value) {
    return photos.value;
  }
  return photos.value.filter(photo => photo.titolo.toLowerCase().includes(searchQuery.value.toLowerCase()));
});
</script>

<template>
  <main>
    <div class="container">

      <h1>Foto</h1>

      <div class="py-3">
        <form @submit.prevent>
          <div class="w-50">
            <div class="input-group">
              <input type="text" class="form-control" placeholder="Cerca foto per titolo" v-model="searchQuery">
              <div class="input-group-append ps-5">
                <button class="btn btn-outline-secondary" type="submit">Cerca</button>
              </div>
            </div>
          </div>
        </form>
      </div>

      <div class="py-3 text-center" v-if="filteredPhotos && filteredPhotos.length === 0">
        <p>Non ci sono risultati</p>
      </div>

      <div class="py-3" v-if="filteredPhotos && filteredPhotos.length > 0">
        <div class="card-deck d-flex flex-wrap gap-5">
          <div class="card" style="width: 250px; height: 425px;" v-for="photo in filteredPhotos" :key="photo.id">
            <div style="height: 150px;">
              <img class="card-img-top" :src="photo.url" :alt="photo.titolo"
                style="width: 100%; height: 100%;object-fit: cover;" />
            </div>
            <div class="card-body" style="height: 275px;">
              <h4 class="card-title text-center">{{ photo.titolo }}</h4>
              <p class="card-text">{{ photo.descrizione }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>

  </main>
</template>
