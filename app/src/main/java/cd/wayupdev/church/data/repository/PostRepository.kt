package cd.wayupdev.church.data.repository

import android.net.Uri
import cd.wayupdev.church.data.model.Post
import cd.wayupdev.church.data.utils.FireBaseConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) {
    private val currentUser by lazy {
        firebaseAuth.currentUser
    }

    @ExperimentalCoroutinesApi
    fun getAll() = callbackFlow {
        firestore.collection("${FireBaseConstants.admins}/${currentUser?.uid.toString()}/${FireBaseConstants.posts}")
            .orderBy(Post::createdAt.name, Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
            if (error != null && value == null) {
                close(error)
            }

            value?.toObjects(Post::class.java).let { posts ->
                if (!isClosedForSend) {
                    trySend(posts)
                }
            }
        }
        awaitClose()
    }.catch {
        throw it
    }.flowOn(Dispatchers.IO)

    suspend fun add(title: String, description: String, date: String, category: String, uri: Uri) {

        val fileRef = storage.reference.child("images/${title}")
        fileRef.putFile(uri).await()
        val imageUrl = fileRef.downloadUrl.await().toString()

        addPostStore(title, description, date, category,imageUrl)

    }

    private suspend fun addPostStore(title: String, description: String, date: String, category: String, imageUrl: String){
        val post = Post(uid = title, title = title, adminUid = currentUser?.uid.toString(), description = description, imageUrl = imageUrl, date = date, category = category, createdAt = Date(System.currentTimeMillis()))
        val doc = firestore.document("${FireBaseConstants.admins}/${currentUser?.uid.toString()}/${FireBaseConstants.posts}/${post.uid}")
        doc.set(post).await()
    }

    suspend fun delete(contactUid: String) {
        firestore.document("${FireBaseConstants.admins}/${currentUser?.uid.toString()}/${FireBaseConstants.posts}/${contactUid}").delete().await()
    }
}