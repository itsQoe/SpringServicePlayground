const addThing = async (event) => {
    event.preventDefault()
    const formData = new FormData(event.target);
    const jsonData = Object.fromEntries(formData.entries())
    event.target.reset()
    const response = await fetch('/things', {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        method: 'PUT',
        body: JSON.stringify(jsonData)
    })
    const responseJson = await response.json()
    await getThings()
    console.log('Response', responseJson)
}

const setToDone = async (id) => {
    const response = await fetch(`/things/${id}/done`, {method: 'POST'})
    const updatedThing = response.json()
    console.log(updatedThing)
}

const ThingLi = (id, name, description, done) => {
    const li = document.createElement('LI')
    const text = document.createTextNode(`${name}: ${description}`)
    const checkbox = document.createElement('input')
    checkbox.setAttribute('type', 'checkbox')
    checkbox.checked = done
    checkbox.onclick = () => setToDone(id)
    li.appendChild(text)
    li.appendChild(checkbox)
    return li
}

const getThings = async () => {
    const thingsRequest = await fetch('/things', {
        headers: {
            "Accept": "application/json"
        },
    })
    const things = await thingsRequest.json()
    const thingsItems = things.map(thing => ThingLi(1, thing.name, thing.description, false))
    const ol = document.getElementById('things-list')
    while (ol.firstChild) {
        ol.removeChild(ol.lastChild)
    }
    ol.append(...thingsItems)
}

getThings()