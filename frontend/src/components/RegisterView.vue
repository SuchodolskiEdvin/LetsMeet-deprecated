<template>
	<v-main>
		<v-container fluid fill-height>
			<v-layout align-center justify-center>
				<v-flex xs12 sm8 md4>

					<v-card-text>
						<v-form>
							<v-text-field prepend-icon="person" name="email" label="E-mail"
								v-model="credentials.email"
								type="text"></v-text-field>
							<v-text-field prepend-icon="lock" name="password" label="Password"
								v-model="credentials.password"
								type="password">
							</v-text-field>
							<v-text-field prepend-icon="lock" name="Confirm password" label="Confirm password"
								v-model="credentials.confirmPassword"
								type="password">
							</v-text-field>
							<v-text-field name="name" label="Name"
								v-model="credentials.name"
								type="text"></v-text-field>
							<v-text-field name="surname" label="Surname"
								v-model="credentials.surname"
								type="text">
							</v-text-field>
						</v-form>
					</v-card-text>
					<v-card-actions>
						<v-row>

							<v-col cols="6"/>
							<v-col offset="1" cols="5">
								<div style="float: right">
									<v-btn color="primary" @click="reg">
										Zarejestruj
									</v-btn>
								</div>
							</v-col>
						</v-row>
					</v-card-actions>
				</v-flex>
			</v-layout>
		</v-container>
	</v-main>
</template>

<script>
	import {api} from "@/util/Api";

	export default {
		name: 'RegisterView',

		data() {
			return {
				credentials: {
					name: "",
					surname: "",
					email: "",
					password: "",
					confirmPassword: "",
				},
				error_message: "asd",
			};
		},

		methods: {
			reg() {
				api.post(this, "/user", this.credentials, () => {
					this.$router.push("/app");
				}, onError => {
					console.log(onError);
				});
			},
		},
	}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
