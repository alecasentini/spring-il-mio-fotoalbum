<script setup>
import { ref } from 'vue';
import axios from "axios";
const API_URL = "http://localhost:8080/api/v1.0/messages"
const message = ref({ email: '', content: '' });

function submitForm() {
    axios.post(API_URL, message.value)
        .then(() => {
            alert("Il tuo messaggio è stato inviato con successo!");
            message.value.email = '';
            message.value.content = '';
        })
        .catch(err => console.log(err));
}
</script>

<template>
    <div class="container">
        <h3>Contattaci</h3>
        <form @submit.prevent="submitForm">
            <div class="mb-3">
                <label for="email" class="form-label">Indirizzo email</label>
                <input type="email" class="form-control" id="email" v-model="message.email" required>
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">Messaggio</label>
                <textarea id="content" rows="3" class="form-control" v-model="message.content" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Invia</button>
        </form>
    </div>
</template>