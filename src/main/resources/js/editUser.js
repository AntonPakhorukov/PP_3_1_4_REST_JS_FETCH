async function sendDataEditUser(user) {
    await fetch("/api/admin" ,
        {method:"POST", headers: {'Content-type': 'application/json'}, body: JSON.stringify(user)})
}

const modalEdit = document.getElementById("editModal");

async function EditModalHandler() {


    const newEditModal = document.getElementById("editModal");
    newEditModal.addEventListener('submit', async function (event) {
        event.preventDefault();
        const Name = newEditModal.querySelector('#name');
        console.log(Name);
    })

    await fillModal(modalEdit);
}

modalEdit.addEventListener("submit", async function(event){
    event.preventDefault();

    const rolesSelected = document.getElementById("rolesEdit");

    const currentRoles = await getUserDataById(document.getElementById("idEdit").value)

    let roles = [];
    if (rolesSelected.selectedOptions.length > 0) {
        for (let option of rolesSelected.selectedOptions) {
            if(option.value === ROLE_USER.name) {
                roles.push(ROLE_USER);
            } else if (option.value === ROLE_ADMIN.name) {
                roles.push(ROLE_ADMIN);
            }
        }
    } else {
        for (let option of currentRoles.roles) {
            if (option.name === ROLE_USER.name) {
                roles.push(ROLE_USER);
            } else if (option.name === ROLE_ADMIN.name) {
                roles.push(ROLE_ADMIN);
            }
        }
    }


    let user = {
        id: document.getElementById("idEdit").value,
        name: document.getElementById("nameEdit").value,
        password: document.getElementById("passwordEdit").value,
        age: document.getElementById("ageEdit").value,
        email: document.getElementById("emailEdit").value,
        roles: roles
    }
    // console.log(document.getElementById("idEdit").value);
    // console.log(document.getElementById("nameEdit").value);
    // console.log(document.getElementById("emailEdit").value);
    // console.log(document.getElementById("ageEdit").value);
    // console.log(document.getElementById("passwordEdit").value);
    // console.log(roles);
    //
    // console.log('Update: ' + user.id + user.name + user.age + user.email + user.roles);

    try {
        await sendDataEditUser(user);
    } catch (error){
        console.error(error.message);
    }
    // await sendDataEditUser(user).catch();
    await fillTableOfAllUsers();

    const modalBootstrap = bootstrap.Modal.getInstance(modalEdit);
    modalBootstrap.hide();
})
