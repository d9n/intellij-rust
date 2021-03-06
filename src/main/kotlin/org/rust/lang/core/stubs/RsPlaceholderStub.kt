package org.rust.lang.core.stubs

import com.intellij.lang.ASTNode
import com.intellij.psi.stubs.*
import org.rust.lang.core.psi.ext.RsCompositeElement

class RsPlaceholderStub(parent: StubElement<*>?, elementType: IStubElementType<*, *>)
    : StubBase<RsCompositeElement>(parent, elementType) {

    class Type<PsiT : RsCompositeElement>(
        debugName: String,
        private val psiCtor: (RsPlaceholderStub, IStubElementType<*, *>) -> PsiT
    ) : RsStubElementType<RsPlaceholderStub, PsiT>(debugName) {

        override fun shouldCreateStub(node: ASTNode): Boolean = createStubIfParentIsStub(node)

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?)
            = RsPlaceholderStub(parentStub, this)

        override fun serialize(stub: RsPlaceholderStub, dataStream: StubOutputStream) {
        }

        override fun createPsi(stub: RsPlaceholderStub) = psiCtor(stub, this)

        override fun createStub(psi: PsiT, parentStub: StubElement<*>?) = RsPlaceholderStub(parentStub, this)

        override fun indexStub(stub: RsPlaceholderStub, sink: IndexSink) {
        }
    }
}
